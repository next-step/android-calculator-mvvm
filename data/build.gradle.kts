plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":domain"))

    Kotlin.run {
        implementation(STDLIB)
        implementation(GSON)
    }
    DaggerHiltConfig.run {
        implementation(CORE)
        kapt(COMPILER)
    }
    Room.run {
        kapt(COMPILER)
        implementation(KTX)
        implementation(RUNTIME)
        testImplementation(TESTING)
        annotationProcessor(COMPILER)
    }
    Test.run {
        testRuntimeOnly(VINTAGE)
        testImplementation(MOCK)
        testImplementation(CORE)
        testImplementation(TRUTH)
        testImplementation(JUNIT4)
        testImplementation(TURBINE)
        testImplementation(JUPITER)
        testImplementation(COROUTINE)
        androidTestImplementation(EXT)
        androidTestImplementation(RUNNER)
        androidTestImplementation(TURBINE)
        androidTestImplementation(COROUTINE)
    }
}
