plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(projects.core.essentials)
    testImplementation(libs.junit)
}