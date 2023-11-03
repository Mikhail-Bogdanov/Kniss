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

task("addPreCommitGitHookOnBuild") {
    println("Running Add Pre Commit Git Hook Script on Build...")
    exec {
        commandLine("cp", "./.scripts/pre-commit", "./.git/hooks")
    }
    println("✅ Added Pre Commit Git Hook Script")
}

task("setAppVersionOnBuild") {
    //setting app version from TXT file
    val currentVersion = File("./.scripts/AppVersion.txt").readText()
    Settings.VersionName = currentVersion
    println("Current App Version: ${Settings.VersionName}")
}