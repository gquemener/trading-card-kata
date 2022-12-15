package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.FetchGame
import me.gildasquemener.tradingcardkata.Game.application.GameProjection
import me.gildasquemener.tradingcardkata.Game.application.GameState
import me.gildasquemener.tradingcardkata.Game.domain.Card
import me.gildasquemener.tradingcardkata.Game.domain.GameHasStarted
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test


class GameProjectionTest {
    @Test
    fun testInitGameState() {
        val subject = GameProjection()

        val gameId = GameId.generate().toString()
        val initialPlayerDeck = (1..17).map { Card(it) }
        val initialPlayerHand = (1..3).map { Card(it) }
        val initialOpponentDeck = (1..17).map { Card(it) }
        val initialOpponentHand = (1..3).map { Card(it) }

        subject.on(GameHasStarted(gameId, initialPlayerDeck, initialPlayerHand, initialOpponentDeck, initialOpponentHand))

        val game = subject.fetch(FetchGame(gameId))

        assertThat(game.id, equalTo(gameId))
        assertThat(game.activePlayer, equalTo(null))
        assertThat(game.player.deckSize, equalTo(initialPlayerDeck.size))
        assertThat(game.player.hand, equalTo(initialPlayerHand.map { GameState.Card(it.manaCost) }))
        assertThat(game.player.mana.available, equalTo(0))
        assertThat(game.player.mana.slots, equalTo(0))
        assertThat(game.player.health, equalTo(30))
        assertThat(game.opponent.deckSize, equalTo(initialOpponentDeck.size))
        assertThat(game.opponent.handSize, equalTo(initialOpponentHand.size))
        assertThat(game.opponent.mana.available, equalTo(0))
        assertThat(game.opponent.mana.slots, equalTo(0))
        assertThat(game.opponent.health, equalTo(30))
    }
}