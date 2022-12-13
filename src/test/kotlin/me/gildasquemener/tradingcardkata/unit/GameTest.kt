package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.*
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var fixture: AggregateTestFixture<Game>;

    private val initialPlayerDeck = listOf(1..20).map { Card(CardId.randomUUID()) }
    private val initialComputerDeck = listOf(1..20).map { Card(CardId.randomUUID()) }

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Game::class.java);

        fixture.registerInjectableResource(object : CardDealer {
            override fun initialPlayerDeck(): List<Card> = initialPlayerDeck
            override fun initialComputerDeck(): List<Card> = initialComputerDeck
        })
    }

    @Test
    fun testStartNewGame() {
        val id = GameId.generate().toString()

        fixture
            .`when`(StartNewGame(id))
            .expectSuccessfulHandlerExecution()
            .expectEvents(GameHasStarted(id, initialPlayerDeck, initialComputerDeck))
    }
}