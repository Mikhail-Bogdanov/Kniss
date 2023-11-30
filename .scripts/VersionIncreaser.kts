import java.io.File

println("*********************")
println("KOTLIN SCRIPT STARTED")
println("*********************")

val path = args[0]

val versionFile = File("${path}/.scripts/AppVersion.txt")

val versionString = versionFile.readText()

var version = versionString.drop(2).toInt()

println("Old Version: $version")

version++

println("New Version: $version")

versionFile.writeText("1.$version")

println("*********************")
println("KOTLIN SCRIPT ENDED")
println("*********************")