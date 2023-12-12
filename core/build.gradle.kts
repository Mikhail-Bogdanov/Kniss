plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.core"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        minSdk = Settings.MinSDK
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
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.ComposeCompilerVersion
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(Dependencies.Compose.ComposeUi)
    implementation(Dependencies.Compose.ComposeViewModel)
    implementation(Dependencies.Navigation.ComposeNavigation)
    implementation(Dependencies.Navigation.ComposeDestinationsAnimationsCore)
    ksp(Dependencies.Navigation.ComposeDestinationsKsp)

    implementation(Dependencies.Orbit.OrbitCompose)
    implementation(Dependencies.Orbit.OrbitAndroid)

    implementation(Dependencies.Koin.KoinCore)
}