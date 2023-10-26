import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.remotedata"
    compileSdk = Settings.CompileSDK

    defaultConfig {
        minSdk = Settings.MinSDK

        val properties = Properties()
        properties.load(project.rootProject.file("keys.properties").inputStream())

        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
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
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(Dependencies.Modules.DomainGray))

    implementation(Dependencies.Network.Retrofit)
    implementation(Dependencies.Network.GsonConverter)
    implementation(Dependencies.Network.OkHttp)
    implementation(Dependencies.Network.LoggingInterceptor)

    implementation(Dependencies.Kotlin.KotlinCoroutines)

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.Android.Annotations)
}