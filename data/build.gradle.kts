plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.messenger.jaber.data"
}

dependencies {
    api(projects.core.essentials)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.datastore.preferences)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}