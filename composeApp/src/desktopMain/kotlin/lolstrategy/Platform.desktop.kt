package lolstrategy

actual fun getPlatform(): Platform = JVMPlatform()

class JVMPlatform : Platform {
    override val name: String = "Jvm version"
}
