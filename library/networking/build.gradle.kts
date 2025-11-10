import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
   alias (libs.plugins.kotlin.ksp)
}

android {
    namespace = "cz.vican.library.networking"
    compileSdk = 36

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin.deps)
    implementation(libs.bundles.retrofit.deps)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    // Moshi Code Generation
    ksp(libs.moshi.codegen)
    testImplementation(libs.junit)
}