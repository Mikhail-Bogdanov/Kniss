plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.localdatawhite"
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
}

dependencies {
    implementation(project(Dependencies.Modules.DomainWhite))

    implementation(Dependencies.Room.RoomRuntime)
    implementation(Dependencies.Room.RoomKotlin)
    ksp(Dependencies.Room.RoomCompiler)

    implementation(Dependencies.Android.Annotations)

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.DataStore.PreferenceDataStore)
}