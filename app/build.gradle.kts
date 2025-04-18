plugins {

    id("com.android.application")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.resea_profesores"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ResenaProfesores"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)

    // Firebase (el BOM maneja las versiones)
    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)
    implementation(libs.firebase.database)

    // Google Sign-In
    implementation(libs.play.services.auth)

    // Credentials API
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
}