buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(Dependencies.Android.Application) version Dependencies.Android.ApplicationVersion apply false
    id(Dependencies.Android.Android) version Dependencies.Android.ApplicationVersion apply false
    id(Dependencies.Kotlin.Kotlin) version Dependencies.Kotlin.KotlinVersion apply false
    id(Dependencies.Ksp.KspGradlePlugin) version Dependencies.Ksp.KspVersion apply false
    id(Dependencies.Jvm.Jvm) version Dependencies.Jvm.JvmVersion apply false
}