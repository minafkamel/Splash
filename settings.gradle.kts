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

dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }


}

rootProject.name = "Splash"
include(":app")
include(":photolist")
include(":photolist:data")
include(":photolist:domain")
include(":photolist:presentation")
include(":common")
include(":common:ui")
include(":common:domain")
