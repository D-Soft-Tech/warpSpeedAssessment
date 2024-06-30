plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.warpspeedassessment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.warpspeedassessment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
    dataBinding {
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    ndkVersion = "24.0.8215888"
    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.coroutines.dependency)
    implementation(libs.retrofit.dependency)
    implementation(libs.gson.converter)
    implementation(libs.dagger.hilt.dependency)
    implementation(libs.room.dependency)
    implementation(libs.paging3.dependency)
    implementation(libs.nav.fragment)
    implementation(libs.nav.ui)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.logging.interceptor.dependency)
    implementation(libs.dprefs.library)
    annotationProcessor(libs.room.annotation.processor.dependency)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}