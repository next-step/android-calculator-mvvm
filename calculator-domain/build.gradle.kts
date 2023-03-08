plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val coroutineVersion = "1.6.4"

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("io.mockk:mockk:1.10.6")

    // Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")
}
