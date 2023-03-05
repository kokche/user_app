enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "UserDataApp"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("lib") {
            from(files("$rootDir/gradle/libs.versions.toml"))
        }
    }
}
include(":app")
