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

    implementation(project(Dependencies.Modules.Gray))
    implementation(project(Dependencies.Modules.White))
    implementation(project(Dependencies.Modules.RemoteData))
    implementation(project(Dependencies.Modules.LocalDataGray))
    implementation(project(Dependencies.Modules.AppDestinations))

    implementation(Dependencies.Android.AppCompat)
    implementation(Dependencies.Android.AndroidCore)
    implementation(Dependencies.Compose.Material)
    implementation(Dependencies.Android.AndroidLifecycle)

    implementation(Dependencies.Compose.ComposeActivity)
    implementation(Dependencies.Compose.ComposeNavigation)

    implementation(Dependencies.Koin.KoinAndroid)
}