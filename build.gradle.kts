plugins {
    kotlin("jvm") version "1.9.0"
}

group = "com.anttieskelinen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}