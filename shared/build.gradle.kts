plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.multiplatformResources)
}

kotlin {
    jvm("desktop")
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val commonMain by getting {
            dependencies {
                api(libs.resources)
                api(libs.resources.compose)
                api(libs.resources.graphics)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
        }

        val desktopMain by getting {
            dependsOn(commonMain)
        }

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.santimattius.kmp.skeleton.shared"
}

android {
    namespace = "com.santimattius.kmp.skeleton.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
