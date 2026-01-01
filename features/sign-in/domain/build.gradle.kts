plugins {
    alias(libs.plugins.custom.kotlin.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.core.essentials)
    //hilt
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
}