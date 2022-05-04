import com.arkivanov.gradle.Target

plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("com.arkivanov.gradle.setup")
}

setupMultiplatform {
    targets(
        Target.Android,
        Target.Jvm,
        Target.Js(mode = Target.Js.Mode.IR),
        Target.Ios(
            arm64 = false, // Uncomment to enable arm64 target
        ),
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":decompose"))
                implementation(deps.reaktive.reaktive)
            }
        }

        named("javaMain") {
            dependencies {
                implementation(project(":extensions-compose-jetbrains"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }

        named("androidMain") {
            kotlin.srcDir("src/javaMain/kotlin") // To make the IDE happy inside the javaMain source set

            dependencies {
//                implementation(project(":extensions-compose-jetpack"))
//                implementation(project(":extensions-android"))
//                implementation(deps.androidx.compose.foundation.foundation)
//                implementation(deps.androidx.compose.material.material)
//                implementation(deps.androidx.compose.ui.uiTooling)
//                implementation(deps.android.material.material)
            }
        }

        named("jvmMain") {
            kotlin.srcDir("src/javaMain/kotlin") // To make the IDE happy inside the javaMain source set
        }

        named("jsMain") {
            dependencies {
                implementation(project.dependencies.enforcedPlatform(deps.jetbrains.kotlinWrappers.kotlinWrappersBom.get()))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-mui")
            }
        }
    }
}

compose {
    web.targets() // Disable Compose for Web
}
