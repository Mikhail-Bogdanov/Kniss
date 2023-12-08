plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.localdatagray"
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
        jvmTarget = Dependencies.Jvm.JvmTarget
    }
}

dependencies {
    implementation(project(Dependencies.Modules.DomainGray))

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.DataStore.PreferenceDataStore)

    implementation(platform(Dependencies.Compose.ComposeBOM))

    //TESTS

    androidTestImplementation(Dependencies.Testing.AndroidJUnit)
    androidTestImplementation(Dependencies.Testing.JUnit)
    androidTestImplementation(Dependencies.Testing.AndroidMonitor)
    androidTestImplementation(Dependencies.Testing.MockitoAndroid)
    androidTestImplementation(Dependencies.Testing.MockitoKotlin)
    androidTestImplementation(Dependencies.Kotlin.KotlinCoroutinesTest)
    androidTestImplementation(Dependencies.Koin.KoinCore)
}