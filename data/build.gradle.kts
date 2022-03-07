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
    }
    DaggerHiltConfig.run {
        kapt(COMPILER)
        implementation(ANDROID)
    }
    Room.run {
        kapt(COMPILER)
        implementation(KTX)
        implementation(RUNTIME)
        testImplementation(TESTING)
    }
    Test.run {
        testRuntimeOnly(VINTAGE)
        testImplementation(MOCKK)
        testImplementation(COROUTINES_TEST)
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
