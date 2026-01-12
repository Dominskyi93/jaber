plugins {
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.messenger.jaber.core.presentation.test"
}

dependencies {
    implementation(libs.androidx.appcompat)
    api(libs.junit)
}