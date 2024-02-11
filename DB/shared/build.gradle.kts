
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization").version("1.9.22")
    id("com.squareup.sqldelight").version("1.5.5")
}

kotlin {
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
            baseName = "Shared"
            isStatic = true
        }
    }

    val coroutinesVersion = "1.7.3"
    val ktorVersion = "2.3.7"
    val sqlDelightVersion = "1.5.5"
    val dateTimeVersion = "0.4.1"

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
            implementation(projects.shared)
            implementation("com.google.android.material:material:1.11.0")
            implementation("androidx.appcompat:appcompat:1.6.1")
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
            implementation("androidx.core:core-ktx:1.12.1")
            implementation("androidx.recyclerview:recyclerview:1.3.2")
            implementation("androidx.cardview:cardview:1.0.0")
        }
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:$ktorVersion")
            implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
        }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.jetbrains.handson.kmm.shared.cache"
    }
}
