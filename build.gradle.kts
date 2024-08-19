plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.24"
}

group = "gs25"
version = "0.0.1-SNAPSHOT"
description = "Hotel-Reservation-Management-System"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.modelmapper:modelmapper:3.2.1")
    implementation("no.tornado.databinding:jxdatepicker-support:1.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.security:spring-security-crypto")

    runtimeOnly("com.h2database:h2")

    compileOnly ("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testCompileOnly ("org.projectlombok:lombok")
    testAnnotationProcessor ("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    runtimeOnly("com.formdev:flatlaf:3.5.1")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
