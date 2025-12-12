pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Jaber"
include(":app")
include(":templates:android-library")
include(":templates:kotlin-library")
include(":templates:feature:domain")
include(":templates:feature:presentation")
include(":core:essentials")
include(":core:common-android")
include(":features:init:domain")
include(":features:init:presentation")
include(":core:theme")
