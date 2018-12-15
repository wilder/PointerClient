import de.dynamicfiles.projects.gradle.plugins.javafx.JavaFXGradlePluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.wilderpereira"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath("de.dynamicfiles.projects.gradle.plugins:javafx-gradle-plugin:8.8.2")
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.10"
}

apply(plugin = "javafx-gradle-plugin")

configure<JavaFXGradlePluginExtension> {
    mainClass = "com.wilderpereira.Application"
    vendor = "Wilder"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}