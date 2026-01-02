plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.messenger.jaber.signin.presentation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(projects.features.signIn.domain)
    implementation(projects.core.essentials)
    implementation(projects.core.theme)
    implementation(projects.core.navigationDsl)
    implementation(projects.core.presentation)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    //container
    implementation("com.elveum:container:2.0.0-beta05")
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}