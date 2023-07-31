//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    application
    jacoco
    id("io.qameta.allure") version "2.11.2"
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
    // Эта одна строка заменяет три внизу - специально сделано Jupiter
//    testImplementation ("org.junit.jupiter:junit-jupiter:5.7.1")
//    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.1")
//    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.1")

    testImplementation("io.mockk:mockk:1.13.5")
    implementation("org.slf4j:slf4j-simple:2.0.7")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport", "allureReport")
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}

application {
    mainClass.set("MainKt")
}
