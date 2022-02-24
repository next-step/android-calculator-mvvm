plugins {
    id("com.android.application")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "edu.nextstep.camp.calculator"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] =
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Kotlin.STDLIB)
    implementation(Material.MATERIAL)
    testImplementation(Others.ROBOLECTRIC)

    Androidx.run {
        implementation(CORE)
        implementation(APP_COMPAT)
        implementation(CONSTRAINTLAYOUT)
        implementation(FRAGMENT)
        implementation(LIFECYCLE)
    }
    Test.run {
        testRuntimeOnly(VINTAGE)
        testImplementation(JUNIT4)
        testImplementation(TRUTH)
        testImplementation(MOCK)
        testImplementation(JUPITER)
        testImplementation(CORE)
        testImplementation(COROUTINE)
        testImplementation(TURBINE)
        androidTestImplementation(EXT)
        androidTestImplementation(ESPRESSO)
        androidTestImplementation(RUNNER)
        androidTestImplementation(JUPITER_API)
        androidTestImplementation(JUNIT5_CORE)
        androidTestRuntimeOnly(JUNIT5_RUNNER)
    }
}
