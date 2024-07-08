package lolstrategy

actual fun getPlatform(): Platform {
    TODO("Not yet implemented")
}

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}
