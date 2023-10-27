plugins {
    id(Dependencies.Android.Android)
    id(Dependencies.Kotlin.Kotlin)
    id(Dependencies.Ksp.Ksp)
}

android {
    namespace = "${Settings.PackageName}.white"
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
    implementation(project(Dependencies.Modules.AppDestinations))
    implementation(project(Dependencies.Modules.DomainWhite))
    implementation(project(Dependencies.Modules.DomainGray))
    implementation(project(Dependencies.Modules.Core))

    implementation(Dependencies.Compose.ComposeUi)
    implementation(Dependencies.Compose.ComposeUiGraphics)
    implementation(Dependencies.Compose.ComposePreview)
    implementation(Dependencies.Compose.ComposeMaterial3)
    implementation(Dependencies.Compose.ComposeIcons)
    implementation(Dependencies.Compose.ComposeViewModel)
    implementation(Dependencies.Compose.ComposeSystemUiController)
    implementation(Dependencies.Compose.ComposeNavigation)
    implementation(Dependencies.Compose.ComposeAnimation)
    implementation(Dependencies.Compose.Material)
    implementation(Dependencies.Compose.GridPad)
    implementation(platform(Dependencies.Compose.ComposeBOM))

    implementation(platform(Dependencies.Kotlin.KotlinBOM))

    implementation(Dependencies.Android.AppCompatResources)
    implementation(Dependencies.Android.AppCompat)
    implementation(Dependencies.Android.AndroidCore)
    implementation(Dependencies.Android.AndroidLifecycle)

    implementation(Dependencies.Koin.KoinAndroid)
    implementation(Dependencies.Koin.KoinCompose)

    implementation(Dependencies.Orbit.OrbitCompose)
    implementation(Dependencies.Orbit.OrbitAndroid)

    implementation(Dependencies.Browser.Browser)
}