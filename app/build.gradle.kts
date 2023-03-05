plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp").version("1.8.10-1.0.9")
    id("kotlin-parcelize")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    compileSdkVersion = libs.versions.compileSdkVersion.get()
    defaultConfig {
        applicationId = "com.gen.userdataapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://reqres.in/\"")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles getDefaultProguardFile ('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {

    implementation(libs.coreKtx)

    implementation(platform(libs.composeBoom))

    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)

    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.activityCompose)
    implementation(libs.kotlinReflect)
    implementation(libs.navigationCompose)

    // Di
    implementation(libs.koinCore)
    implementation(libs.koinCompose)

    // Data
    implementation(libs.bundles.moshi)

    // Room
    implementation(libs.bundles.room)
    implementation(libs.roomPaging)
    implementation(libs.pagingRuntime)
    annotationProcessor(libs.roomCompiler)
    ksp(libs.roomCompiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitMoshi)
    implementation(libs.okhttp)
    implementation(libs.okhttpInterceptor)

    // Tests
    testImplementation(libs.recyclerView)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.mockito)
    androidTestImplementation(libs.roomTest)
    androidTestImplementation(libs.coroutineTest)
    androidTestImplementation(libs.testRuner)
    testImplementation(libs.junit)
    implementation(libs.junitKtx)
// optional - Test helpers
    testImplementation(libs.coroutineTest)
    testImplementation(libs.mockito)
}