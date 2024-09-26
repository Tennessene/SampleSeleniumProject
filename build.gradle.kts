plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "com.acclash"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.25.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
}

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        archiveClassifier.set("") // Ensures the generated JAR is not appended with '-all'
        manifest {
            attributes(
                "Main-Class" to "com.acclash.Main"
            )
        }
    }

    build {
        dependsOn(shadowJar)
    }
}