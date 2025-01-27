plugins {
   id("com.android.library")
   id("org.jetbrains.kotlin.android")
   id("maven-publish")
}

android {
   namespace = "ru.spektrit.pdfcompose"
   compileSdk = 34

   defaultConfig {
      minSdk = 24

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      consumerProguardFiles("consumer-rules.pro")
   }

   buildFeatures {
      compose = true
   }

   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
      }
   }

   composeOptions {
      kotlinCompilerExtensionVersion = "1.4.3"
   }

   compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   kotlinOptions {
      jvmTarget = "1.8"
   }
   publishing {
      singleVariant("release") {
         withSourcesJar()
      }
   }
}

dependencies {

   implementation("androidx.core:core-ktx:1.12.0")
   implementation("androidx.appcompat:appcompat:1.6.1")
   implementation("com.google.android.material:material:1.10.0")
   testImplementation("junit:junit:4.13.2")
   androidTestImplementation("androidx.test.ext:junit:1.1.5")
   androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

   implementation(platform("androidx.compose:compose-bom:2023.10.01"))
   implementation("androidx.compose.ui:ui")
   implementation("androidx.compose.ui:ui-graphics")
   implementation("androidx.compose.material3:material3")

   // Coil
   implementation("io.coil-kt:coil-compose:2.4.0")

   // Retrofit 2
   implementation("com.squareup.retrofit2:retrofit:2.9.0")
   implementation("com.squareup.okhttp3:okhttp:4.11.0")
}

publishing {
   publications {
      register<MavenPublication>("release") {
         groupId = "ru.spektrit"
         artifactId = "pdfcompose"
         version = "0.1.0"

         afterEvaluate {
            from(components["release"])
         }
      }
   }
}
