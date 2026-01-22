plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.messenger.jaber.data"
}

dependencies {
    api(projects.core.essentials)
    implementation(projects.core.data)
    implementation(projects.features.signIn.domain)
    implementation(projects.features.signup.domain)
    implementation(projects.features.chats.domain)
    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    //container
    implementation(libs.container)
    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    implementation(libs.datastore.preferences)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}