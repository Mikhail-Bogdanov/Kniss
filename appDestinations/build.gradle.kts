plugins {
    id(Dependencies.Jvm.JavaLibrary)
    id(Dependencies.Jvm.Jvm)
}

java {
    sourceCompatibility = Dependencies.Jvm.CompileJavaVersion
    targetCompatibility = Dependencies.Jvm.CompileJavaVersion
}