plugins {
    id("com.android.application")
    id("kotlin-android")
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
        testImplementation(CORE)
        androidTestImplementation(EXT)
        androidTestImplementation(ESPRESSO)
    }
}
