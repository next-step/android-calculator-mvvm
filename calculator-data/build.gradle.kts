plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "camp.nextstep.edu.calculator"
    compileSdk = 33

    defaultConfig {
        minSdk = Version.minSdk
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
    implementation("androidx.test:core-ktx:1.4.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    annotationProcessor("androidx.room:room-compiler:${Version.room}")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:${Version.room}")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${Version.room}")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:${Version.room}")

    implementation ("com.google.code.gson:gson:${Version.gson}") // gson

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}