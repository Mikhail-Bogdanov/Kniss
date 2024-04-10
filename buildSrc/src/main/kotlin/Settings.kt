object Settings {
    const val PackageName = "com.evoteam"
    const val CompileSDK = 34
    const val MinSDK = 26
    const val TargetSDK = 34

    const val MajorVersion = 1
    const val MinorVersion = 0
    const val PatchCode = 0

    const val VersionCode = 10_000 * MajorVersion + 100 * MinorVersion + PatchCode

    const val VersionName = VersionCode.toString()
}