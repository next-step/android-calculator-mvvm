object Kotlin {
    private const val KOTLIN_VERSION = "1.6.10"

    const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
}

object Others {
    const val ROBOLECTRIC = "org.robolectric:robolectric:4.6.1"
}

object Androidx {
    const val CORE = "androidx.core:core-ktx:1.7.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.1"
    const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:1.4.1"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
}

object Test {
    const val JUNIT4 = "junit:junit:4.13.2"
    const val TRUTH = "com.google.truth:truth:1.1.3"
    const val MOCK = "io.mockk:mockk:1.12.2"
    const val EXT = "androidx.test.ext:junit:1.1.3"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:3.4.0"
    const val CORE = "androidx.arch.core:core-testing:2.1.0"
    const val VINTAGE = "org.junit.vintage:junit-vintage-engine:5.8.2"
    const val JUPITER = "org.junit.jupiter:junit-jupiter:5.8.2"
    const val RUNNER = "androidx.test:runner:1.4.0"
    const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:5.8.2"
    const val JUNIT5_CORE = "de.mannodermaus.junit5:android-test-core:1.2.2"
    const val JUNIT5_RUNNER = "de.mannodermaus.junit5:android-test-runner:1.2.2"
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0"
    const val TURBINE = "app.cash.turbine:turbine:0.7.0"
}

object Material {
    const val MATERIAL = "com.google.android.material:material:1.5.0"
}

object Room {
    private const val ROOM_VERSION = "2.4.1"

    const val RUNTIME = "androidx.room:room-runtime:$ROOM_VERSION"
    const val COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    const val KTX = "androidx.room:room-ktx:$ROOM_VERSION"
    const val TESTING = "androidx.room:room-testing:$ROOM_VERSION"
}

object DaggerHiltConfig {
    private const val HILT_VERSION = "2.41"

    const val ANDROID_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$HILT_VERSION"
    const val ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
    const val COMPILER = "com.google.dagger:hilt-compiler:$HILT_VERSION"
    const val CORE = "com.google.dagger:hilt-core:$HILT_VERSION"
}

