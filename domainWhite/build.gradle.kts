plugins {
    id(Dependencies.Jvm.JavaLibrary)
    id(Dependencies.Jvm.Jvm)
}

java {
    sourceCompatibility = Dependencies.Jvm.CompileJavaVersion
    targetCompatibility = Dependencies.Jvm.CompileJavaVersion
}

dependencies {
    implementation(Dependencies.Koin.KoinCore)
    implementation(Dependencies.Kotlin.KotlinCoroutines)
    implementation(Dependencies.Android.Annotations) //is this ok?
}