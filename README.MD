# Sample project for testing GraalVM configs of az sdk in Spring environment.

## How to

### 1. Build and run using Buildpacks(using Docker, no GraalVM dependencies)

#### 1.1 Prerequisites
[Install Docker](https://docs.docker.com/get-docker/#installation)

#### 1.2 Build
mvnw -Pnative spring-boot:build-image -DskipTests

#### 1.3 Run
docker run --rm -p 8080:8080 docker.io/library/demo:0.0.1-SNAPSHOT

### 2. (Alternative to 1) Build and run using Native Build Tools

#### 2.1 Prerequisites
[Install GraalVM for JDK](https://www.oracle.com/downloads/graalvm-downloads.html?selected_tab=1)

#### 2.2 Build 
mvnw -Pnative native:compile -DskipTests

#### 2.3 Run
target\demo

### 3. (Optional) Verify that the configs work in SDK module(instead of current app module)

#### 3.1 Local build resource-manager

- Clone https://github.com/Azure/azure-sdk-for-java
- CD to `sdk/resourcemanager`
- Copy GraalVM configs from current project's `src/main/resources/META-INF` to `azure-resourcemanager` module's `src/main/resources/META-INF`
- Delete current project's `src/main/resources/META-INF` folder
- Under `sdk/resourcemanager`, run `mvn install -f pom.xml -Dcheckstyle.skip -Dgpg.skip -Dmaven.javadoc.skip -Drevapi.skip -DskipSpringITs -DskipTests -Dspotbugs.skip -Djacoco.skip`

#### 3.2 Switch azure-resourcemanager dependency to local build artifact

- Get the local build artifact version in `sdk/resourcemanager/azure-resourcemanager/pom.xml`
- In current project's `pom.xml`, change `azure-resourcemanager` dependency version to the version in previous step

#### 3.3 Build and Run

## Reference
- [Initialized using Spring Initializr](https://start.spring.io/)
- [Spring Native Images](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image.developing-your-first-application.buildpacks)
- Test case from [az-java-sdk-graalvm](https://github.com/weidongxu-microsoft/az-java-sdk-graalvm)