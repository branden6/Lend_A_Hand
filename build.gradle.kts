// Top-level build.gradle.kts (usually in the root project folder)

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
}

val googleApiKey: String? = rootProject.findProperty("google_api_key") as String?