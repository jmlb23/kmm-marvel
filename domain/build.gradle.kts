plugins {
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    ios {
        binaries {
            framework {
                baseName = "Shared"
            }
        }
    }

    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val commonTest by getting {
            dependencies{
                implementation(kotlin("stdlib"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinNativeLink>().configureEach {
    kotlinOptions.freeCompilerArgs += "-Xobjc-generics"
}