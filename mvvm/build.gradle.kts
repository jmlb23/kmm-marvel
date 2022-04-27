import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain"))
                implementation(project(":data"))
                implementation("io.insert-koin:koin-core:3.2.0-beta-1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies {
                dependsOn(commonMain)
                implementation("io.insert-koin:koin-android:3.2.0-beta-1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
            }
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}
