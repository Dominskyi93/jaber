plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.messenger.jaber.glue"
}

dependencies {
    implementation(projects.data)
    implementation(projects.features.init.presentation)
    implementation(projects.features.signIn.presentation)
    implementation(projects.features.signup.presentation)
    implementation(projects.features.chats.presentation)
    implementation(projects.features.settings.presentation)
    implementation(projects.features.chatRoom.presentation)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.container)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}