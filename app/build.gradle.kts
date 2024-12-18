plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

javafx {
    version = "21.0.5"
    modules("javafx.graphics", "javafx.fxml", "javafx.controls")
}

// testing {
//     suites {
//         val test by getting(JvmTestSuite::class) {
//             useJUnitJupiter("5.9.1")
//         }
//     }
// }

application {
    mainModule = "io.github.alphameo.darr_visualization.visualization"
    mainClass = "io.github.alphameo.darr_visualization.visualization.DemoApplication"
}

tasks.test {
    useJUnitPlatform()
}
