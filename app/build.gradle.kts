plugins {
    id(Dependencies.Android.Application)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.qwertyuiop"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        applicationId = "${Settings.PackageName}.qwertyuiop"
        minSdk = Settings.MinSDK
        targetSdk = Settings.TargetSDK
        versionCode = Settings.VersionCode
        versionName = Settings.VersionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = Dependencies.Jvm.CompileJavaVersion
        targetCompatibility = Dependencies.Jvm.CompileJavaVersion
    }
    kotlinOptions {
        jvmTarget = Dependencies.Jvm.JvmTarget
    }
    bundle {
        storeArchive {
            enable = false
        }
    }
}

dependencies {
    implementation(project(Dependencies.Modules.EntryPoint))
    implementation(project(Dependencies.Modules.Gray))
    implementation(project(Dependencies.Modules.LocalDataGray))
    implementation(project(Dependencies.Modules.LocalDataWhite))
    implementation(project(Dependencies.Modules.RemoteData))
    implementation(project(Dependencies.Modules.White))
    implementation(project(Dependencies.Modules.DomainGray))
    implementation(project(Dependencies.Modules.DomainWhite))

    implementation(Dependencies.Koin.KoinAndroid)
}