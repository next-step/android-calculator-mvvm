plugins {
    id("com.android.application")
    id("kotlin-android")
    id("de.mannodermaus.android-junit5")
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
        viewBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Material.MATERIAL)

    Androidx.run {
        implementation(STDLIB)
        implementation(CORE)
        implementation(APP_COMPAT)
        implementation(CONSTRAINTLAYOUT)
        implementation(FRAGMENT)
    }
    Test.run {
        testImplementation(JUNIT4)
        testImplementation(TRUTH)
        testImplementation(MOCK)
        androidTestImplementation(EXT)
        androidTestImplementation(ESPRESSO)
        testImplementation(VINTAGE)
        testRuntimeOnly(JUPITER)
        androidTestImplementation(RUNNER)
        androidTestImplementation(JUPITER_API)
        androidTestImplementation(JUNIT5_CORE)
        androidTestRuntimeOnly(JUNIT5_RUNNER)
    }
}
