package lolstrategy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
