import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.graalvm.buildtools.native") version "0.9.20"
	kotlin("jvm") version "1.8.20"
	kotlin("plugin.spring") version "1.8.20"
	id("org.openapi.generator") version "6.3.0"
}

group = "se.collectum"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven("https://repo1.maven.org/maven2")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	compileOnly("org.openapitools:openapi-generator-gradle-plugin:6.3.0") {
		exclude("ch.qos.logback", "logback-classic")
		exclude("org.slf4j", "slf4j-simple")
	}

	implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val generatedSourcesDir = "$buildDir/generated/openapi"

openApiGenerate {
	generatorName.set("spring")

	inputSpec.set("$projectDir/inrapportering-openapi-v0.yml")
	outputDir.set("$buildDir/generated/sources/openapi")

	apiPackage.set("se.collectum.palev.rest.api")
	modelPackage.set("se.collectum.palev.rest.model")

	configOptions.set(mapOf(
			"dateLibrary" to "java8",
			"interfaceOnly" to "true",
			"library" to "spring-boot",
			"openApiNullable" to "false",
			"serializableModel" to "true",
			"useBeanValidation" to "true",
			"useTags" to "true",
			"implicitHeaders" to "true",
			"openApiNullable" to "false",
			"oas3" to "true",
			"legacyDiscriminatorBehavior" to "false",
			"useSpringBoot3" to "true",
			"useSwaggerUI" to "false",
			"skipDefaultInterface" to "true"
	))
}

sourceSets {
	main {
		java {
			srcDir("${buildDir}/generated/sources/openapi/src/main/java")
		}
	}
}

tasks.compileKotlin {
	dependsOn("openApiGenerate")
}

/*
// Se nedan för mer information om Spring Boot Gradle plugin
// https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/

tasks.named<BootBuildImage>("bootBuildImage") {
	val bpConf = mapOf("BP_BINARY_COMPRESSION_METHOD" to "upx") // upx or gzexe

	imageName.set("collectum.se/library/${project.name}")
	environment.set(environment.get() + bpConf)
}
*/