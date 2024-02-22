plugins {
    id("java")
}

allprojects {
    group = "com.github.ppzxc"
    version = "0.0.3"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.9.1"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.assertj:assertj-core:3.24.2")
    }

    tasks.test {
        useJUnitPlatform()
    }
}