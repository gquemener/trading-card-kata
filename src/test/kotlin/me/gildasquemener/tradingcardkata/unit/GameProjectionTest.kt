package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.FetchGame
import me.gildasquemener.tradingcardkata.Game.application.GameProjection
import me.gildasquemener.tradingcardkata.Game.application.GameState
import me.gildasquemener.tradingcardkata.Game.domain.Card
import me.gildasquemener.tradingcardkata.Game.domain.CardId
import me.gildasquemener.tradingcardkata.Game.domain.GameHasStarted
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.junit.jupiter.api.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*


class GameProjectionTest {
    @Test
    fun testInitGameState() {
        val subject = GameProjection()

        val gameId = GameId.generate().toString()
        val initialPlayerDeck = listOf(1..20).map { Card(CardId.randomUUID()) }
        val initialComputerDeck = listOf(1..20).map { Card(CardId.randomUUID()) }

        subject.on(GameHasStarted(gameId, initialPlayerDeck, initialComputerDeck));

        TODO("Pass initial player deck to game state")
        TODO("Set deck size to game state")
        TODO("Set initial mana to players")
        assertThat(
            subject.fetch(FetchGame(gameId)),
            equalTo(GameState(gameId))
        )
    }
}