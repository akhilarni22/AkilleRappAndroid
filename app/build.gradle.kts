plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.akillerapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.akillerapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation ("com.rudderstack.android.sdk:core:1.7+")
// add the following line if you don't have Gson included already
    implementation ("com.google.code.gson:gson:2+")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.gms:google-services:4.3.15")
    implementation("com.android.support:support-annotations:28.0.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
}