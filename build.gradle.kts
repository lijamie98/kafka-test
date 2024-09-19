plugins { id("java") }

group = "org.stellar"

version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
  // add kafka clients
  implementation("org.apache.kafka:kafka-clients:3.6.1")
}

tasks.test { useJUnitPlatform() }
