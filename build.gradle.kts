import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "me.zeeger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}
application {
    mainClassName = "MainKt"
}