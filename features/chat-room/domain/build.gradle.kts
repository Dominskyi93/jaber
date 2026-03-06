plugins {
    alias(libs.plugins.custom.kotlin.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.core.essentials)
    //container
    implementation(libs.container)

    implementation(libs.javax.inject)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
}