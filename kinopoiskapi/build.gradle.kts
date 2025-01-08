plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
}
kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    api(libs.kotlinx.serialization.json)
    api(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.annotation)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit.adapters.result)
}
