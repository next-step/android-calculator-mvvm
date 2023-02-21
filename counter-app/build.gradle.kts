@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "camp.nextstep.edu.calculator"
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")
    implementation("androidx.core:core-ktx:${Version.CORE_KTX}")
    implementation("androidx.appcompat:appcompat:${Version.APP_COMPAT}")
    implementation("com.google.android.material:material:${Version.MATERIAL}")
    implementation("androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}")
    implementation("androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}")

    // Junit
    testImplementation("junit:junit:${Version.JUNIT4}")
    testImplementation("com.google.truth:truth:${Version.TRUTH}")
    testImplementation("androidx.test.ext:junit:${Version.EXT_JUNIT}")

    // Live data test
    testImplementation("androidx.arch.core:core-testing:${Version.CORE_TESTING}")

    // Ui test
    testImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}")

}
