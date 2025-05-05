plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.9.0"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
// Runtime dependencies
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // HTTP client for API calls
    implementation("io.insert-koin:koin-core:3.5.6") // Dependency injection
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1") // Coroutines
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0") // JSON parsing and config loading (updated)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23") // Kotlin standard library (explicitly added for clarity)

// Test dependencies
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.23") // Kotlin test framework
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.23") // JUnit 5 for Kotlin tests
    testImplementation("io.insert-koin:koin-test:3.5.6") { // Koin testing
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit") // Avoid conflict with kotlin-test-junit5
    }
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1") // Coroutines testing
    testImplementation("io.mockk:mockk:1.13.11") // Mocking for tests
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

jacoco {
    toolVersion = "0.8.10"
}


tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        html.required.set(true)
    }

    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    exclude("**/entity/**", "**/di/**", "**/exceptions/**", "**/presentation/**")

                }
            }
        )
    )
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)

    violationRules {
        rule {
            limit {
                minimum = "0.75".toBigDecimal() // 100% coverage required
            }
        }
    }

    classDirectories.setFrom(
        files(
            fileTree("build/classes/kotlin/main") {
                exclude("**/entity/**", "**/di/**", "**/exceptions/**", "**/presentation/**")
            }
        )
    )
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
    dependsOn(tasks.jacocoTestCoverageVerification)
}