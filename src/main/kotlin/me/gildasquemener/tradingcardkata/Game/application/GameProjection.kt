package me.gildasquemener.tradingcardkata.Game.application

import me.gildasquemener.tradingcardkata.Game.domain.GameHasStarted
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class GameProjection {
    private val games: MutableMap<String, GameState> = HashMap();

    @EventHandler
    fun on(event: GameHasStarted) {
        games[event.gameId] = GameState(
            event.gameId,
            GameState.PlayerState(
                event.initialPlayerDeck.size,
                event.initialPlayerHand.map { GameState.Card(it.manaCost) }
            )
        )
    }

    @QueryHandler
    fun fetch(query: FetchGame): GameState {
        return games[query.id]!!;
    }
}