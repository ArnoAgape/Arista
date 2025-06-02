// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Plugin version applied via settings.gradle.kts or version catalog
    id("com.android.application") version "8.9.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    id ("com.google.devtools.ksp") version  "2.1.21-2.0.1"
    id ("androidx.room") version "2.7.1" apply false
}
