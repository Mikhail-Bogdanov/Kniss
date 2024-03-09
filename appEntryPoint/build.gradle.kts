plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.entrypoint"
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
        jvmTarget = Dependencies.Jvm.CompileJavaVersion.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.ComposeCompilerVersion
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(Dependencies.Modules.Domain))
    implementation(project(Dependencies.Modules.Core))
    implementation(project(Dependencies.Modules.Presentation))

    implementation(Dependencies.Compose.ComposeActivity)
    implementation(Dependencies.Android.AppCompat)
    implementation(Dependencies.Compose.ComposeViewModel)
    implementation(Dependencies.Navigation.ComposeNavigation)
    implementation(Dependencies.Navigation.ComposeDestinationsAnimationsCore)
    ksp(Dependencies.Navigation.ComposeDestinationsKsp)
    implementation(Dependencies.Compose.ComposeMaterial3)
    implementation(platform(Dependencies.Compose.ComposeBOM))

    implementation(Dependencies.Orbit.OrbitCompose)

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)
}