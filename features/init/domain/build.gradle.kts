plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(projects.core.essentials)
    implementation(libs.javax.inject)
    testImplementation(libs.junit)
}