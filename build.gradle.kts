import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


group = "no.nav.sykepenger.kontrakter.kafka"
version = "0.1-SNAPSHOT"

plugins {
    idea
    kotlin("jvm") version "1.3.41"
    "maven-publish"
}

dependencies {
    implementation(kotlin("stdlib"))
}

repositories {
    mavenCentral()
}

tasks {
    create("printVersion") {
        doLast {
            println(project.version)
        }
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
