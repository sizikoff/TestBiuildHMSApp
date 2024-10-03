plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.first.testbiuildhmsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.first.testbiuildhmsapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // Настройка flavorDimensions
    flavorDimensions.add("store")

    productFlavors {
        create("gms") {
            dimension = "store"
            applicationIdSuffix = ".gms"
            versionNameSuffix = "-gms"
        }

        create("hms") {
            dimension = "store"
            applicationIdSuffix = ".hms"
            versionNameSuffix = "-hms"
        }
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    // Зависимости для GMS
    "gmsImplementation"("com.google.android.gms:play-services-base:18.1.0")
    "gmsImplementation"("com.google.firebase:firebase-analytics:21.3.0")

    // Зависимости для HMS
    "hmsImplementation"("com.huawei.hms:base:5.3.0.300")
    "hmsImplementation"("com.huawei.hms:hianalytics:5.2.1.300")

    // Остальные зависимости для Compose и проекта
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Тестовые зависимости
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}