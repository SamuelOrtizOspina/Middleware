
# Upgrade Plan: correo-messaging (20260309202621)

- **Generated**: 2026-03-09T20:26:21Z
- **HEAD Branch**: N/A (no commits yet)
- **HEAD Commit ID**: N/A


## Available Tools

- **JDKs**
  - JDK 17: currently used for baseline (detected via compilation release 17)
  - JDK 21: available at C:\Program Files\Microsoft\jdk-21.0.8.9-hotspot\bin (required for final validation)
- **Build Tools**
  - Maven: available via PATH (no wrapper present)

## Guidelines

- Upgrade only the Java runtime to version 21; no dependency upgrades unless required by this change.
- Maintain existing project structure and functionality.


## Options

- Working branch: appmod/java-upgrade-20260309202621
- Run tests before and after the upgrade: true


## Upgrade Goals

- Upgrade Java from 17 to 21

### Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java runtime | 17 | 21 | User requested upgrade |
| Spring Boot | 3.3.0 | 3.3.0 | Already compatible with Java 21 |
| spring-boot-starter-amqp | 3.3.0 | 3.3.0 | - |
| spring-boot-starter | 3.3.0 | 3.3.0 | - |

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java runtime | 17 | 21 | User requested upgrade |
| Spring Boot | 3.3.0 | 3.3.0 | Already compatible with Java 21 |
| spring-boot-starter-amqp | 3.3.0 | 3.3.0 | - |
| spring-boot-starter | 3.3.0 | 3.3.0 | - |


### Derived Upgrades

None required for this upgrade.

## Upgrade Steps

- **Step 1: Setup Environment**
  - **Rationale**: Ensure required JDKs and Maven are available before making changes.
  - **Changes to Make**:
    - [ ] Confirm JDK 21 is accessible and JAVA_HOME can be set.
    - [ ] Verify Maven runs with JDK 21.
  - **Verification**:
    - Command: `#list_jdks` and `mvn -v` with JAVA_HOME=JDK21
    - Expected: Maven reports Java version 21 and two JDKs listed.

- **Step 2: Setup Baseline**
  - **Rationale**: Record current compilation success before modifying project.
  - **Changes to Make**:
    - [ ] Compile project with existing `<java.version> = 17` using JDK 17.
  - **Verification**:
    - Command: `mvn clean test-compile -q` (with JAVA_HOME pointing at JDK21 but compiler release 17) 
    - JDK: 17
    - Expected: BUILD SUCCESS (confirmed earlier).

- **Step 3: Upgrade Java version property**
  - **Rationale**: The main goal is bumping the source/target level to 21.
  - **Changes to Make**:
    - [ ] Change `<java.version>` in `pom.xml` from `17` to `21`.
    - [ ] Ensure maven-compiler-plugin honors release level (no explicit config currently).
  - **Verification**:
    - Command: `grep -n "<java.version>" pom.xml` to confirm update.
    - Expected: property set to 21.

- **Step 4: Compile with Java 21**
  - **Rationale**: Verify project compiles successfully against JDK 21 and target level.
  - **Changes to Make**:
    - [ ] Build project using JDK21 (JAVA_HOME set accordingly).
  - **Verification**:
    - Command: `mvn clean test-compile -q` with JAVA_HOME=JDK21
    - JDK: 21
    - Expected: BUILD SUCCESS (compiles with release 21).

- **Step 5: Final Validation**
  - **Rationale**: Confirm upgrade goals met and no regressions.
  - **Changes to Make**:
    - [ ] Run full build with JDK 21.
  - **Verification**:
    - Command: `mvn clean test` (tests none) with JAVA_HOME=JDK21
    - JDK: 21
    - Expected: BUILD SUCCESS, no tests fail (none present).

      - `#install_jdk version=21` (if upgrading to Java 21)
    - **Changes to Make**:
      - [ ] Verify all target versions in pom.xml/build.gradle
      - [ ] Resolve ALL TODOs and temporary workarounds from previous steps
      - [ ] Clean rebuild with target JDK
      - [ ] Fix any remaining compilation errors
      - [ ] Run full test suite and fix ALL test failures (iterative fix loop until 100% pass) — **skip if "Run tests before and after the upgrade: false" in Options**
    - **Verification**:
      - Command: `mvn clean test -q` (if tests enabled) or `mvn clean test-compile -q` (if tests disabled)
      - JDK: C:\Program Files\Microsoft\jdk-21.0.8.9-hotspot\bin
      - Expected: Compilation SUCCESS + 100% tests pass (if tests enabled)

## Key Challenges

- **Updating java.version property**: must ensure maven compiler uses release 21 and project compiles.
- **Lack of tests**: verification limited to compilation; absence of tests increases risk of runtime issues post-upgrade. Manual inspection of execution will be required.
     - **Strategy**: Upgrade incrementally: Hibernate 5.6.x with Spring Boot 2.7.x, validate, then Hibernate 6.x with Spring Boot 3.2.x. Update jackson-datatype-hibernate4 → hibernate6 module. Expect schema validation and query adjustments.
-->
