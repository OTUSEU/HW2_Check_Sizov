//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    application
    jacoco
    id("io.qameta.allure") version "2.11.2"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("io.mockk:mockk:1.13.5")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    // https://mvnrepository.com/artifact/org.spekframework.spek2/spek-dsl-jvm
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.19")
    // https://mvnrepository.com/artifact/org.spekframework.spek2/spek-runner-junit5
    testImplementation("org.spekframework.spek2:spek-runner-junit5:2.0.19")


}

tasks.test {
    useJUnitPlatform{
        includeEngines = setOf("spek2")
    }
    finalizedBy("jacocoTestReport", "allureReport")
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}

application {
    mainClass.set("MainKt")
}
