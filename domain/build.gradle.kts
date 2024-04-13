plugins {
    id(Dependencies.Jvm.JavaLibrary)
    id(Dependencies.Jvm.Jvm)
    id(Dependencies.Serialization.SerializationPlugin)
}

java {
    sourceCompatibility = Dependencies.Jvm.CompileJavaVersion
    targetCompatibility = Dependencies.Jvm.CompileJavaVersion
}

dependencies {
    implementation(Dependencies.Koin.KoinCore)
    implementation(Dependencies.Kotlin.KotlinCoroutines)
    implementation(Dependencies.Kotlin.KotlinCoroutinesTest)

    implementation(Dependencies.Serialization.Serialization)

    testImplementation(Dependencies.Testing.JUnit)
    testImplementation(Dependencies.Testing.MockitoKotlin)
}