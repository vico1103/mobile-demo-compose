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

    versionCatalogs {
        create("libs")
    }
}

rootProject.name = "ScratchApp"
include(":app")
include(":library:networking")
include(":library:architecture")
include(":library:localStorage")
include(":generic:activate")
include(":generic:scratchcard")
include(":feature:scratch")
include(":feature:activate")
include(":feature:main")
include(":generic:design")
