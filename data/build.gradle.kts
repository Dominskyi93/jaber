plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.messenger.jaber.data"
}

dependencies {
    implementation(projects.core.essentials)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}