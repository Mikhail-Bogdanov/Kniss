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
include(":appEntryPoint")
include(":data:remoteData")
include(":data:localDataGray")
include(":data:localDataWhite")
include(":presentation:white")
include(":presentation:gray")
include(":presentation:entryPoint")
include(":domain:domainGray")
include(":domain:domainWhite")
include(":core")
