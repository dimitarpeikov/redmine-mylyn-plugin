# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

An Eclipse plugin that adds a Mylyn task connector for Redmine. Mylyn calls into our code at well-defined extension points; we translate to and from Redmine's REST API. The Redmine *server-side* component is **not in this repo** — see [README.md](README.md) for the historical naming inversion ("server connector" vs. "Eclipse plugin").

## Build & test

- `mvn clean verify` — full Tycho build; produces a P2 update site at [net.sf.redmine_mylyn.p2repository/target/repository](net.sf.redmine_mylyn.p2repository/target/repository).
- Target platform is picked via `-Dplatform-version-name=<id>`; the default is `2025-09`. Other profiles defined in [pom.xml](pom.xml): `helios`, `indigo`, `kepler`, `luna`, `mars`, `neon`. Each maps to a corresponding `eclipse-site` and Mylyn version range.
- `.target` definitions live in [net.sf.redmine_mylyn.feature/](net.sf.redmine_mylyn.feature/): [platform_2025-09.target](net.sf.redmine_mylyn.feature/platform_2025-09.target), [platform_neon.target](net.sf.redmine_mylyn.feature/platform_neon.target), [platform_juno.target](net.sf.redmine_mylyn.feature/platform_juno.target). The `2025-09` target hard-codes Eclipse SDK 4.37 / Mylyn 4.8 and a **local absolute fallback path** (`/home/mitko/bin/eclipse-jee-2509`) — be aware of it if you touch that file.
- Requires Maven ≥ 3.3.9 and a JDK matching the bundles' `JavaSE-21` `Bundle-RequiredExecutionEnvironment`.

## Test modules — read this before running tests

The directories [net.sf.redmine_mylyn.api-test/](net.sf.redmine_mylyn.api-test/) and [net.sf.redmine_mylyn.core-test/](net.sf.redmine_mylyn.core-test/) exist on disk but are **not listed in the root `<modules>` section of [pom.xml](pom.xml)** — `mvn clean verify` from the root does NOT build or run them. To run a test module, build it directly (e.g. `mvn -f net.sf.redmine_mylyn.core-test/pom.xml test`) or temporarily add the module to the parent POM. Some tests assume a live Redmine server; [CONTRIBUTING.md](CONTRIBUTING.md) references vagrant test VMs at `https://github.com/ljader/redmine-mylyn-vagrant-boxes`.

[net.sf.redmine_mylyn.core.extension.timesheet_extensions_plugin/](net.sf.redmine_mylyn.core.extension.timesheet_extensions_plugin/) and [net.sf.redmine_mylyn.extensions.feature/](net.sf.redmine_mylyn.extensions.feature/) are likewise excluded from the parent POM and must be built explicitly when needed.

## Architecture

Modules form a strict downward dependency chain:

```
ui   →  core  →  api  →  common
feature aggregates {api, core, ui, common}; p2repository wraps feature for P2.
```

- **[net.sf.redmine_mylyn.common/](net.sf.redmine_mylyn.common/)** — `ILogService` logging abstraction (SLF4J + Logback) and shared utilities. No Mylyn or Redmine knowledge.
- **[net.sf.redmine_mylyn.api/](net.sf.redmine_mylyn.api/)** — Redmine REST client and domain model. `IRedmineApiClient` / `Api_2_7_ClientImpl` over Apache HttpClient + `mylyn.commons.net`. JAXB-based unmarshalling via `JaxbParser<T>` plus specialised parsers (`IssueParser`, `AttachmentParser`, `SettingsParser`). Query DSL: `Query`, `QueryField`, `QueryFilter`, `CompareOperator`. **No Mylyn `TaskData` knowledge belongs here** — keep this layer Mylyn-free.
- **[net.sf.redmine_mylyn.core/](net.sf.redmine_mylyn.core/)** — the Mylyn bridge. `RedmineRepositoryConnector extends AbstractRepositoryConnector`; `RedmineTaskDataHandler extends AbstractTaskDataHandler`; `IssueMapper` / `RedmineTaskMapper` do bidirectional `Issue ↔ TaskData` translation; `RedmineAttachmentHandler` covers attachments. **Compiled with AspectJ** via `aspectj-maven-plugin` — `.aj` files participate in the build, so plain `javac` will not reproduce what Tycho emits. Declares the `net.sf.redmine_mylyn.core.redmineextension` extension point, consumed by the timesheet extension plugin.
- **[net.sf.redmine_mylyn.ui/](net.sf.redmine_mylyn.ui/)** — UI contributions: `RedmineRepositoryConnectorUi`, `RedmineRepositorySettingsPage`, `RedmineTaskEditorPageFactory`, the status-transition menu. Wired via [net.sf.redmine_mylyn.ui/plugin.xml](net.sf.redmine_mylyn.ui/plugin.xml) into `org.eclipse.mylyn.tasks.ui.repositories`, `…ui.editors`, and `org.eclipse.ui.menus`.
- **[net.sf.redmine_mylyn.feature/](net.sf.redmine_mylyn.feature/)** — `feature.xml` aggregator + `.target` definitions.
- **[net.sf.redmine_mylyn.p2repository/](net.sf.redmine_mylyn.p2repository/)** — P2 update-site generation (`category.xml`).
- **[net.sf.redmine_mylyn/](net.sf.redmine_mylyn/)** — near-empty branding/manifest bundle.

## Gotchas

- **AspectJ in `core`**: anything you change in [net.sf.redmine_mylyn.core/](net.sf.redmine_mylyn.core/) must round-trip through the AspectJ compile step; verify with `mvn -pl net.sf.redmine_mylyn.core -am verify` after non-trivial edits.
- **Versioning is duplicated** across [pom.xml](pom.xml) (`0.5.2.YYYYMMDD-SNAPSHOT`), each module's `MANIFEST.MF` / `pom.xml`, and [net.sf.redmine_mylyn.feature/feature.xml](net.sf.redmine_mylyn.feature/feature.xml) (OSGi `0.5.2.qualifier`). Bumps must be applied consistently in all three.
- **Runtime logging** is configured *outside* this repo at `{eclipse-workspace}/.metadata/.plugins/net.sf.redmine_mylyn.common/logback.xml` (template in [README.md](README.md)). HTTP wire logs go through commons-logging, not SLF4J, so they will **not** appear in that logback file.
- Releases use `unleash-maven-plugin` 2.6.0 (configured in `pluginManagement` in [pom.xml](pom.xml)), not the standard `maven-release-plugin`.
