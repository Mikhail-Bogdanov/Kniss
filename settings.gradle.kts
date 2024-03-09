pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "main_template"
include(":app")
include(":appEntryPoint")
include(":data:remoteData")
include(":data:localData")
include(":presentation")
include(":domain")
include(":core")
