plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("io.mockk:mockk:1.10.6")
}
