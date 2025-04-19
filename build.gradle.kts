plugins {
	kotlin("jvm") version "1.9.25"

	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.slf4j:slf4j-api:2.0.7")
	implementation("ch.qos.logback:logback-classic:1.4.7") // ✅ Recommended logger


	implementation("org.springframework.boot:spring-boot-starter:3.2.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")

	implementation("org.springframework.boot:spring-boot-starter-web") // REST APIs
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA with MySQL
	runtimeOnly("mysql:mysql-connector-java:8.0.33") // MySQL Driver
	implementation("org.springframework.boot:spring-boot-starter-validation") // Input validation

	// Spring Security & Authentication
	implementation("org.springframework.boot:spring-boot-starter-security") // Basic security
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5") // JWT Authentication
	implementation("org.springframework.security:spring-security-crypto") // BCrypt password hashing
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client") // OAuth2 (Google, GitHub login)

	// AI Chat Bot APIs (Gemini & CoinGecko)
	implementation("com.squareup.retrofit2:retrofit:2.9.0") // API calls
	implementation("com.squareup.retrofit2:converter-gson:2.9.0") // JSON conversion

	// API Performance & Optimization
	implementation("org.springframework.boot:spring-boot-starter-cache") // Caching (Redis)
	implementation("org.springframework.data:spring-data-redis") // Redis caching support
	//implementation("com.github.vladimir-bukhtoyarov:bucket4j-spring-boot-starter:1.5.1") // Rate Limiting

	// Payment Gateways (Razorpay & Stripe)
	implementation("com.razorpay:razorpay-java:1.4.1")
	implementation("com.stripe:stripe-java:22.6.0")

	// Microservices & Communication (Future Scope)
	implementation("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign") // Feign Client
	implementation("org.springframework.kafka:spring-kafka") // Kafka for event-driven architecture

	// Deployment & Monitoring
	implementation("org.springframework.boot:spring-boot-starter-actuator") // Monitoring
	implementation("net.logstash.logback:logstash-logback-encoder:7.0") // ELK Logging

	// Kotlin & Spring Boot Support
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Development Tools
	developmentOnly("org.springframework.boot:spring-boot-devtools") // Hot reload
	testImplementation("org.springframework.boot:spring-boot-starter-test") // Testing

}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
