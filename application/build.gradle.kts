plugins {
	id("java")
	alias(libs.plugins.springboot)
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(projects.andinaExchangeDomain)

	implementation(libs.spring.security)

	compileOnly(libs.annotations)
	compileOnly(libs.slf4j)
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}