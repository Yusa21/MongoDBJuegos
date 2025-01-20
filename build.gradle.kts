plugins {
    kotlin("jvm") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.mongodb:mongodb-driver-kotlin-sync:5.3.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")
}

kotlin {
    jvmToolchain(8)
}

tasks.test {
    useJUnitPlatform()
}