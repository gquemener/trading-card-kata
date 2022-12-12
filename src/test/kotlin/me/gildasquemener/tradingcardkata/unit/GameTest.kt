package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.Game
import me.gildasquemener.tradingcardkata.Game.domain.GameHasStarted
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var fixture: AggregateTestFixture<Game>;

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Game::class.java);
    }

    @Test
    fun testStartNewGame() {
        val id = GameId.generate().toString()
        fixture
            .`when`(StartNewGame(id))
            .expectSuccessfulHandlerExecution()
            .expectEvents(GameHasStarted(id))
    }
}