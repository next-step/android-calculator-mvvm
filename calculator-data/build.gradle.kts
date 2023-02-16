plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "camp.nextstep.edu.calculator"
    compileSdk = 33

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":calculator-domain"))

    implementation("androidx.room:room-runtime:${Version.room}")
    annotationProcessor("androidx.room:room-compiler:${Version.room}")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:${Version.room}")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${Version.room}")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:${Version.room}")

    implementation ("com.google.code.gson:gson:${Version.gson}") // gson

}