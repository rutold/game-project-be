plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
    id ("org.sonarqube") version "5.0.0.4638"
    id ("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
jacoco {
    toolVersion = "0.8.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.security:spring-security-test:6.3.1")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation ("org.flywaydb:flyway-mysql")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
    implementation("org.jacoco:org.jacoco.core:0.8.7")
    implementation ("mysql:mysql-connector-java:8.0.33")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "io.r2dbc")
    }
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation ("org.flywaydb:flyway-core")
    implementation ("com.h2database:h2")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly ("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}
tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

sonar {
    properties {
        property("sonar.host.url", "http://localhost:9000/")
        property("sonar.projectName", "Eternal Strife")
        property("sonar.projectKey", "Eternal-Strife")
        property("sonar.token", "squ_aaf6403497656452fda5d4e8b0ce4a3f42dc5315")
        property("sonar.sources", "src/main/java")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.qualitygate.wait", true)
    }
}



