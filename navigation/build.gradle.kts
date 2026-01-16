plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.messenger.jaber.navigation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.navigationDsl)
    implementation(projects.core.essentials)
    implementation(projects.features.init.presentation)
    implementation(projects.features.signIn.presentation)
    implementation(projects.features.signup.presentation)
    implementation(projects.features.chats.presentation)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.material.icons.extended)
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