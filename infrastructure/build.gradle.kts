plugins {
	id("java")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(projects.andinaExchangeDomain)
	implementation(projects.andinaExchangeApplication)

	implementation(libs.hibernate)
	implementation(libs.postgresql)

	implementation(libs.spring.web)
	implementation(libs.spring.security)
	implementation(libs.spring.orm)
	implementation(libs.tomcat)

	implementation(libs.jjwt.api)
	runtimeOnly(libs.jjwt.impl)
	runtimeOnly(libs.jjwt.jackson)

	implementation("io.github.cdimascio:dotenv-java:3.0.2")

	compileOnly(libs.annotations)
	compileOnly(libs.slf4j)
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

tasks {
	withType<JavaCompile> {
		options.compilerArgs.add("-parameters")
	}
}
