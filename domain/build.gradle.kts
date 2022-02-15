plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(Androidx.STDLIB)
    Test.run {
        testRuntimeOnly(VINTAGE)
        testImplementation(JUPITER)
        testImplementation(JUNIT4)
        testImplementation(TRUTH)
    }
}