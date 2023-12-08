plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.gray"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        minSdk = Settings.MinSDK
    }

    ksp {
        arg(
            Dependencies.Navigation.ComposeDestinationsMode,
            Dependencies.Navigation.ModeDestinations
        )
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
    implementation(project(Dependencies.Modules.DomainGray))
    implementation(project(Dependencies.Modules.Core))

    implementation(Dependencies.Compose.ComposeMaterial3)
    implementation(Dependencies.Compose.Material)
    implementation(Dependencies.Compose.ComposeIcons)
    implementation(Dependencies.Compose.ComposeViewModel)
    implementation(Dependencies.Compose.ComposePermissionsAccompanist)
    implementation(Dependencies.Compose.ComposeWebViewAccompanist)
    implementation(Dependencies.Compose.ComposeSystemUiController)
    implementation(Dependencies.Compose.ComposeDestinationsCore)
    ksp(Dependencies.Compose.ComposeDestinationsKsp)
    implementation(platform(Dependencies.Compose.ComposeBOM))

    implementation(platform(Dependencies.Kotlin.KotlinBOM))

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.Network.OkHttp)

    implementation(Dependencies.OneSignal.OneSignal)

    implementation(Dependencies.Orbit.OrbitAndroid)
    implementation(Dependencies.Orbit.OrbitCompose)
}