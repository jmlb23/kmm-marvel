plugins {
    kotlin("multiplatform")
    id("com.codingfeline.buildkonfig")
    kotlin("plugin.serialization") version "1.6.20"
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
            dependencies{
                implementation(project(":domain"))
                implementation(kotlin("stdlib"))
                implementation("io.ktor:ktor-client-core:2.0.0")
                implementation("io.ktor:ktor-client-cio:2.0.0")
                implementation("io.ktor:ktor-client-logging:2.0.0")
                implementation("io.ktor:ktor-client-serialization:2.0.0")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
                implementation("io.insert-koin:koin-core:3.2.0-beta-1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.insert-koin:koin-test:3.2.0-beta-1")
            }
        }
    }
}

buildkonfig {
    packageName = "com.github.jmlb23.marvel"

    defaultConfigs {
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "URL_BASE",
            "https://gateway.marvel.com/v1/public"
        )
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_KEY",
            project.findProperty("API_KEY").toString()
        )
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_KEY_PRIVATE",
            project.findProperty("API_KEY_PRIVATE").toString()
        )
    }

    defaultConfigs("dev") {
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "URL_BASE",
            "https://gateway.marvel.com/v1/public"
        )
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_KEY",
            project.findProperty("API_KEY").toString()
        )
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "API_KEY_PRIVATE",
            project.findProperty("API_KEY_PRIVATE").toString()
        )
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