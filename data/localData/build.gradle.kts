plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
    id(Dependencies.Serialization.SerializationPlugin)
}

android {
    namespace = "${Settings.PackageName}.localdata"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        minSdk = Settings.MinSDK

        testInstrumentationRunner = Dependencies.Testing.TestRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            consumerProguardFiles(
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Dependencies.Jvm.CompileJavaVersion
        targetCompatibility = Dependencies.Jvm.CompileJavaVersion
    }
    kotlinOptions {
        jvmTarget = Dependencies.Jvm.CompileJavaVersion.toString()
    }
}

dependencies {
    implementation(project(Dependencies.Modules.Domain))

    implementation(Dependencies.Room.RoomRuntime)
    implementation(Dependencies.Room.RoomKotlin)
    ksp(Dependencies.Room.RoomCompiler)

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.DataStore.PreferenceDataStore)

    implementation(platform(Dependencies.Compose.ComposeBOM))

    implementation(Dependencies.Serialization.Serialization)

    //TESTS

    androidTestImplementation(Dependencies.Testing.AndroidJUnit)
    androidTestImplementation(Dependencies.Testing.JUnit)
    androidTestImplementation(Dependencies.Testing.AndroidMonitor)
    androidTestImplementation(Dependencies.Testing.MockitoAndroid)
    androidTestImplementation(Dependencies.Testing.MockitoKotlin)
    androidTestImplementation(Dependencies.Kotlin.KotlinCoroutinesTest)
    androidTestImplementation(Dependencies.Koin.KoinCore)

    testImplementation(Dependencies.Testing.JUnit)
    testImplementation(Dependencies.Kotlin.KotlinCoroutinesTest)
    testImplementation(Dependencies.Testing.MockitoKotlin)
    testImplementation(Dependencies.Testing.MockitoCore)
}