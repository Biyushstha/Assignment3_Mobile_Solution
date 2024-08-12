plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.assignment3_biyushshrestha"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assignment3_biyushshrestha"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "OMDB_API_KEY", "\"${project.findProperty("OMDB_API_KEY") ?: ""}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


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

    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Image Loading
    implementation(libs.glide)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    annotationProcessor(libs.compiler)

    // ViewModel and LiveData
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Kotlin Standard Library
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
