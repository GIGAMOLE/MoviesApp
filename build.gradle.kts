buildscript {
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
		classpath("com.android.tools.build:gradle:7.0.0-beta03")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
	}
}

tasks.register("clean", Delete::class) {
	delete(rootProject.buildDir)
}
