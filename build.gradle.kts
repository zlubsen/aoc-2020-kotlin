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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}
tasks.named<Test>("test") {
    useJUnitPlatform()
}
application {
    mainClassName = "MainKt"
}