package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.FetchGame
import me.gildasquemener.tradingcardkata.Game.application.GameProjection
import me.gildasquemener.tradingcardkata.Game.application.GameState
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
        subject.on(GameHasStarted(gameId));

        assertThat(
            subject.fetch(FetchGame(gameId)),
            equalTo(GameState(gameId))
        )
    }
}