import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	kotlin("jvm") version "2.0.10"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"

	id("io.gitlab.arturbosch.detekt") version ("1.23.7")
	id("org.openapi.generator") version "7.8.0"

	idea
}

group = "com.example"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

idea.module {
	generatedSourceDirs.addAll(listOf(
		File("${project.rootDir}/src/main/jooq"),
		File("${project.rootDir}/src/main/openapi"),
	))
}
sourceSets {
	main {
		java {
			srcDir("${project.rootDir}/src/main/openapi")
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// https://detekt.dev/docs/gettingstarted/gradle#kotlin-dsl-3
detekt {
	toolVersion = "1.23.7"

	// https://detekt.dev/docs/gettingstarted/cli#use-the-cli
	// --auto-correct, The additional 'formatting' rule set, added with '--plugins', does support it and needs this flag.
	autoCorrect = true

	// detekt config に対して上書きしたいものだけを config/detekt/detekt.yml に記載する
	buildUponDefaultConfig = true
	config.setFrom("config/detekt/detekt.yml", "config/detekt/detekt-formatting.yml")

	// https://detekt.dev/docs/gettingstarted/gradle/#dependencies
	configurations.all {
		resolutionStrategy.eachDependency {
			if (requested.group == "org.jetbrains.kotlin") {
				useVersion(io.gitlab.arturbosch.detekt.getSupportedKotlinVersion())
			}
		}
	}
}

val openApiPath = "${project.rootDir}/src/main/resources/openapi/openapi.yml"

// https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc#openapivalidate
openApiValidate {
	inputSpec.set(openApiPath)
}

// https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc#openapigenerate
tasks.withType<GenerateTask> {
	doFirst {
		delete("${project.rootDir}/src/main/openapi")
	}
	generatorName.set("kotlin-spring")
	inputSpec.set(openApiPath)
	outputDir.set("${project.rootDir}")

	packageName.set("kiyotakeshi.com.example.generated.openapi")
	modelPackage.set("kiyotakeshi.com.example.generated.openapi.model")

	typeMappings.set(
		mapOf("DateTime" to "LocalDateTime")
	)
	importMappings.set(
		mapOf("LocalDateTime" to "java.time.LocalDateTime")
	)

	// https://openapi-generator.tech/docs/generators/kotlin-spring/#config-options
	configOptions.set(
		mapOf(
			"annotationLibrary" to "none",
			"documentationProvider" to "none",
			"exceptionHandler" to "false",
			"gradleBuildFile" to "false",
			"interfaceOnly" to "true",
			"useSpringBoot3" to "true",
			"sourceFolder" to "src/main/openapi",
		)
	)

	// https://openapi-generator.tech/docs/globals
	globalProperties.set(
		mapOf(
			"models" to "",
			"apis" to "false",
			"supportingFiles" to "false"
		)
	)
}
