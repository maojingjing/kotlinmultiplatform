class Greeting {
    private val platform = getPlatform()

    fun greet(): List<String> = buildList {
        add(if (kotlin.random.Random.nextBoolean()) "Hi!" else "Hello!")
        add("Guess what this is! > ${platform.name.reversed()}!")
    }
}