plugins {
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.messenger.jaber.core.presentation.test"
}

dependencies {
    api(projects.core.presentation)
    api(projects.core.essentials)

    api(libs.androidx.appcompat)
    api(libs.junit)
    api(libs.mockk)
    api(libs.coroutines.test)
    api(libs.flow.test)
}