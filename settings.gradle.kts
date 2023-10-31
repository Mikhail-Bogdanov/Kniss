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

//TODO CREATE BRANCH
//TODO SET ID
rootProject.name = "main_template"
include(":app")
include(":entryPoint")
include(":remoteData")
include(":localDataGray")
include(":localDataWhite")
include(":white")
include(":gray")
include(":appDestinations")
include(":domainGray")
include(":domainWhite")
include(":core")
