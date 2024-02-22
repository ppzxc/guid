rootProject.name = "guid"

val modules: MutableList<Module> = mutableListOf()

fun module(name: String, path: String) {
    modules.add(Module(name, "$rootDir/$path"))
}

data class Module(
    val name: String,
    val path: String
)

module(name = ":guid-core", path = "/guid-core")

modules.forEach {
    include(it.name)
    project(it.name).projectDir = file(it.path)
}