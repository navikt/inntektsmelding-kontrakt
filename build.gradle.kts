import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.TimeZone


val dateFormat = SimpleDateFormat("yyyy.MM.dd-hh-mm")
dateFormat.timeZone = TimeZone.getTimeZone(ZoneId.of("Europe/Oslo"))
val gitHash = System.getenv("GITHUB_SHA")?.takeLast(5) ?: "local-build"
val javaTimeAdapterVersion = "1.1.3"

group = "no.nav.sykepenger.kontrakter"
version = "${dateFormat.format(Date())}-$gitHash"

plugins {
    kotlin("jvm") version "1.6.0"
    java
    id("org.sonarqube") version "2.8"
    id("maven-publish")
 }

 sonarqube {
     properties {
         property("sonar.projectKey", "navikt_inntektsmelding-kontrakt")
         property("sonar.organization", "navikt")
         property("sonar.host.url", "https://sonarcloud.io")
         property("sonar.login", System.getenv("SONAR_TOKEN"))
     }
 }

val jacksonVersion = "2.13.1"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.migesok", "jaxb-java-time-adapters", javaTimeAdapterVersion)
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("javax.validation:validation-api:2.0.1.Final")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

repositories {
    mavenCentral()
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
