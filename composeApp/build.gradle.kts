import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    targets.configureEach {
        compilations.configureEach {
            compilerOptions.configure {
                freeCompilerArgs.addAll("-opt-in=kotlin.ExperimentalUnsignedTypes,kotlin.RequiresOptIn", "-Xexpect-actual-classes")
            }
        }
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            // Compose
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Ktor
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
        }

        jvmMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.java)
        }

        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Navigation
            implementation(libs.androidx.navigation.navigationCompose)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            api(libs.koin.annotations)

            // Ktor
            implementation(libs.bundles.ktor.common)

            // Date Time
            implementation(libs.kotlinx.datetime)

            // Coil
            implementation(libs.coil.compose.core)
            implementation(libs.coil.compose)
            implementation(libs.coil)
            implementation(libs.coil.network.ktor)

            // Storage
            implementation(libs.androidx.datastore.preferences)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Compose Cupertino
            implementation(libs.cupertino)
        }

        commonTest.dependencies {
            // Testing
            implementation(libs.kotlin.test)
        }
    }

    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
    arg("KOIN_CONFIG_CHECK","true")
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "org.jackappsdev.leetcode"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.jackappsdev.leetcode"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
