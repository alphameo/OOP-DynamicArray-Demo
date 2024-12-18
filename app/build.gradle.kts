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

application {
    mainModule = "io.github.alphameo.darray.visualization"
    mainClass = "io.github.alphameo.darray.visualization.DemoApplication"
}

tasks.test {
    useJUnitPlatform()
}
