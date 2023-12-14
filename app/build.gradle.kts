plugins {
    id(Dependencies.Android.Application)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.asdfghjkl"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        applicationId = "${Settings.PackageName}.asdfghjkl"
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
        jvmTarget = Dependencies.Jvm.CompileJavaVersion.toString()
    }
    bundle {
        storeArchive {
            enable = false
        }
    }
    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(project(Dependencies.Modules.AppEntryPoint))
    implementation(project(Dependencies.Modules.Gray))
    implementation(project(Dependencies.Modules.LocalDataGray))
    implementation(project(Dependencies.Modules.LocalDataWhite))
    implementation(project(Dependencies.Modules.RemoteData))
    implementation(project(Dependencies.Modules.White))
    implementation(project(Dependencies.Modules.EntryPoint))
    implementation(project(Dependencies.Modules.DomainGray))
    implementation(project(Dependencies.Modules.DomainWhite))
    implementation(project(Dependencies.Modules.Core))

    implementation(Dependencies.Koin.KoinAndroid)
}