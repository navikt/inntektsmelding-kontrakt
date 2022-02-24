import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


val dateFormat = SimpleDateFormat("yyyy.MM.dd-hh-mm")
dateFormat.timeZone = TimeZone.getTimeZone(ZoneId.of("Europe/Oslo"))
val gitHash = System.getenv("GITHUB_SHA")?.takeLast(5) ?: "local-build"
val javaTimeAdapterVersion = "1.1.3"

group = "no.nav.sykepenger.kontrakter"
version = "${dateFormat.format(Date())}-$gitHash"

plugins {
    kotlin("jvm") version "1.5.30"
    java
    id("maven-publish")
}

val jacksonVersion = "2.9.9"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.migesok", "jaxb-java-time-adapters", javaTimeAdapterVersion)
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("javax.validation:validation-api:2.0.0.Final")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

repositories {
    mavenCentral()
    jcenter()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

configure<PublishingExtension> {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/navikt/inntektsmelding-kontrakt")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {

            pom {
                name.set("inntektsmelding-kontrakt")
                description.set("Kontrakt for utveksling av inntektsmelding i PO-Helse")
                url.set("https://github.com/navikt/inntektsmelding-kontrakt")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/navikt/inntektsmelding-kontrakt.git")
                    developerConnection.set("scm:git:https://github.com/navikt/inntektsmelding-kontrakt.git")
                    url.set("https://github.com/navikt/inntektsmelding-kontrakt")
                }
            }
            from(components["java"])
        }
    }
}

tasks.named<Test>("test") {
    include("no/nav/inntektsmelding/**")
}

tasks.test {
    useJUnitPlatform()
}