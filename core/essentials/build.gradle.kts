plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    api(libs.javax.inject)
    api(libs.coroutines.core)
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.flow.test)
}