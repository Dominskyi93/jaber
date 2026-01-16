import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

fun Project.setupKotlin(jvmTarget: JvmTarget = JvmTarget.JVM_11) {
    val ext = extensions.findByType<KotlinAndroidProjectExtension>()
        ?: extensions.findByType<KotlinJvmProjectExtension>()
    ext?.compilerOptions {
        this.jvmTarget.set(jvmTarget)
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}