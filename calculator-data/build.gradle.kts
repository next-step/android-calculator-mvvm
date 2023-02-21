plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}
android {
    compileSdk = Version.compileSdk

    defaultConfig {
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
}


dependencies {
    implementation(project(":calculator-domain"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")
    implementation("androidx.core:core-ktx:${Version.CORE_KTX}")
    implementation("androidx.appcompat:appcompat:${Version.APP_COMPAT}")
    implementation("com.google.android.material:material:${Version.MATERIAL}")
    implementation("androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}")
    implementation("androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}")

    // Room
    implementation("androidx.room:room-runtime:${Version.ROOM}")
    implementation("androidx.room:room-ktx:${Version.ROOM}")

    // Junit
    testImplementation("junit:junit:${Version.JUNIT4}")
    testImplementation("com.google.truth:truth:${Version.TRUTH}")
    testImplementation("androidx.test.ext:junit:${Version.EXT_JUNIT}")

    // Live data test
    testImplementation("androidx.arch.core:core-testing:${Version.CORE_TESTING}")

    // Ui test
    testImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}")

    //Android test
    androidTestImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO_CORE}")
    androidTestImplementation("androidx.test.ext:junit:${Version.EXT_JUNIT}")

    // Mockk
    testImplementation("io.mockk:mockk:${Version.MOCKK}")

    //Room test
    kapt("androidx.room:room-compiler:2.5.0")
}
