plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.messenger.templates.feature.presentation"
}

dependencies {
    api(projects.features.init.domain)

    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)
    implementation(projects.core.essentials)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}