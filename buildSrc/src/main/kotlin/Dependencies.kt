import Dependencies.Compose.ComposeDestinationsVersion
import Dependencies.Compose.ComposeNavigationVersion
import org.gradle.api.JavaVersion

object Dependencies {

    object Modules {
        const val RemoteData = ":data:remoteData"
        const val LocalData = ":data:localData"
        const val Presentation = ":presentation"
        const val AppEntryPoint = ":appEntryPoint"
        const val Domain = ":domain"
        const val Core = ":core"
    }

    object Android {
        const val ApplicationVersion = "8.3.2"
        const val Application = "com.android.application"
        const val AppCompatVersion = "1.6.1"
        const val AndroidLifecycleVersion = "2.5.1"
        const val AndroidCoreVersion = "1.10.1"
        const val AnnotationsVersion = "1.6.0"

        const val AndroidCore = "androidx.core:core-ktx:$AndroidCoreVersion"
        const val AndroidLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$AndroidLifecycleVersion"
        const val AppCompat = "androidx.appcompat:appcompat:$AppCompatVersion"
        const val AppCompatResources = "androidx.appcompat:appcompat-resources:$AppCompatVersion"
        const val Annotations = "androidx.annotation:annotation-jvm:$AnnotationsVersion"
        const val Android = "com.android.library"
    }

    object Compose {
        const val AccompanistVersion = "0.31.3-beta"
        const val ComposeCompilerVersion = "1.5.4"
        const val ComposeBomVersion = "2023.10.01"
        const val ComposeActivityVersion = "1.8.2"
        const val ComposeViewModelVersion = "2.6.1"

        const val ComposeNavigationVersion = "2.6.0"
        const val ComposeDestinationsVersion = "1.9.55"

        const val MaterialVersion = "1.11.0"

        const val ComposeBOM = "androidx.compose:compose-bom:$ComposeBomVersion"
        const val ComposeActivity = "androidx.activity:activity-compose:$ComposeActivityVersion"
        const val ComposeUi = "androidx.compose.ui:ui"
        const val ComposeUiGraphics = "androidx.compose.ui:ui-graphics"
        const val ComposePreview = "androidx.compose.ui:ui-tooling-preview"
        const val ComposeMaterial3 = "androidx.compose.material3:material3"
        const val ComposeIcons = "androidx.compose.material:material-icons-extended"
        const val ComposeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$ComposeViewModelVersion"
        const val ComposeAnimation = "androidx.compose.animation:animation"
        const val ComposeNavAnimationAccompanist = "com.google.accompanist:accompanist-navigation-animation:$AccompanistVersion"
        const val ComposeWebViewAccompanist = "com.google.accompanist:accompanist-webview:$AccompanistVersion"
        const val ComposePermissionsAccompanist = "com.google.accompanist:accompanist-permissions:$AccompanistVersion"
        const val ComposeUiTooling = "androidx.compose.ui:ui-tooling"
        const val Material = "com.google.android.material:material:$MaterialVersion"
        const val ComposeTracing = "androidx.compose.runtime:runtime-tracing:1.0.0-alpha05"
    }

    object Navigation {
        const val ComposeNavigation = "androidx.navigation:navigation-compose:$ComposeNavigationVersion"
        const val ComposeDestinationsCore = "io.github.raamcosta.compose-destinations:core:$ComposeDestinationsVersion"
        const val ComposeDestinationsKsp = "io.github.raamcosta.compose-destinations:ksp:$ComposeDestinationsVersion"
        const val ComposeDestinationsAnimationsCore = "io.github.raamcosta.compose-destinations:animations-core:$ComposeDestinationsVersion"

        const val ComposeDestinationsMode = "compose-destinations.mode"
        const val ModeDestinations = "destinations"
    }

    object Testing {
        const val JUnitVersion = "4.13.2"
        const val MockitoCoreVersion = "5.7.0"
        const val MockitoKotlinVersion = "4.0.0"
        const val AndroidMonitorVersion = "1.6.1"

        const val JUnit = "junit:junit:$JUnitVersion"
        const val AndroidJUnit = "androidx.compose.ui:ui-test-junit4"

        const val AndroidMonitor = "androidx.test:monitor:$AndroidMonitorVersion"

        const val MockitoCore = "org.mockito:mockito-core:$MockitoCoreVersion"
        const val MockitoKotlin = "org.mockito.kotlin:mockito-kotlin:$MockitoKotlinVersion"
        const val MockitoAndroid = "org.mockito:mockito-android:$MockitoCoreVersion"

        const val TestRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Jvm {
        const val JvmVersion = "1.8.0"
        val CompileJavaVersion = JavaVersion.VERSION_17

        const val Jvm = "org.jetbrains.kotlin.jvm"
        const val JavaLibrary = "java-library"
    }

    object Kotlin {
        const val KotlinVersion = "1.9.20"
        const val KotlinCoroutinesVersion = "1.7.3"

        const val KotlinBOM = "org.jetbrains.kotlin:kotlin-bom:$KotlinVersion"
        const val KotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$KotlinCoroutinesVersion"
        const val KotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$KotlinCoroutinesVersion"
        const val Kotlin = "org.jetbrains.kotlin.android"
    }

    object Serialization {
        const val SerializationPluginVersion = "1.9.20"

        const val SerializationPlugin = "org.jetbrains.kotlin.plugin.serialization"

        const val SerializationVersion = "1.6.3"
        const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$SerializationVersion"
    }

    object Room {
        const val RoomVersion = "2.5.2"

        const val RoomRuntime = "androidx.room:room-runtime:$RoomVersion"
        const val RoomKotlin = "androidx.room:room-ktx:$RoomVersion"
        const val RoomCompiler = "androidx.room:room-compiler:$RoomVersion"
    }

    object Network {
        const val RetrofitVersion = "2.9.0"
        const val OkHttpVersion = "5.0.0-alpha.3"

        const val Retrofit = "com.squareup.retrofit2:retrofit:$RetrofitVersion"
        const val GsonConverter = "com.squareup.retrofit2:converter-gson:$RetrofitVersion"
        const val OkHttp = "com.squareup.okhttp3:okhttp:$OkHttpVersion"
        const val LoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$OkHttpVersion"
    }

    object Koin {
        const val KoinVersion = "3.4.3"
        const val KoinComposeVersion = "3.4.6"

        const val KoinAndroid = "io.insert-koin:koin-android:$KoinVersion"
        const val KoinCore = "io.insert-koin:koin-core:$KoinVersion"
        const val KoinWorkManager = "io.insert-koin:koin-androidx-workmanager:$KoinVersion"
        const val KoinCompose = "io.insert-koin:koin-androidx-compose:$KoinComposeVersion"
    }

    object WorkManager {
        const val WorkManagerVersion = "2.8.1"

        const val WorkManagerRuntime = "androidx.work:work-runtime-ktx:$WorkManagerVersion"
    }

    object Ksp {
        const val KspVersion = "1.9.20-1.0.14"

        const val KspGradlePlugin = "com.google.devtools.ksp"
        const val Ksp = "com.google.devtools.ksp"
    }

    object Coil {
        const val CoilCompose = "io.coil-kt:coil-compose:2.4.0"
    }

    object Paging {
        const val PagingComposeVersion = "3.2.0"
        const val PagingRuntimeVersion = "3.1.1"

        const val PagingCompose = "androidx.paging:paging-compose:$PagingComposeVersion"
        const val PagingRuntime = "androidx.paging:paging-runtime:$PagingRuntimeVersion"
    }

    object Orbit {
        const val OrbitVersion = "6.0.0"

        const val OrbitAndroid = "org.orbit-mvi:orbit-viewmodel:$OrbitVersion"
        const val OrbitCompose = "org.orbit-mvi:orbit-compose:$OrbitVersion"
        const val OrbitCore = "org.orbit-mvi:orbit-core:$OrbitVersion"
    }

    object DataStore {
        const val DataStoreVersion = "1.0.0"

        const val PreferenceDataStore = "androidx.datastore:datastore-preferences:$DataStoreVersion"
        const val CoreDataStore = "androidx.datastore:datastore-preferences-core:$DataStoreVersion"
        const val ProtoDataStore = "androidx.datastore:datastore:$DataStoreVersion"
    }

    object DesugarLibrary {
        const val DesugalLibraryVersion = "2.0.3"

        const val DesugarLibrary = "com.android.tools:desugar_dk_libs:$DesugalLibraryVersion"
    }

    object Browser {
        const val BrowserVersion = "1.5.0"
        const val Browser = "androidx.browser:browser:$BrowserVersion"
    }
}