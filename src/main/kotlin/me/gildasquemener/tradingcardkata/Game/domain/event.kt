package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class GameHasStarted(
    @JsonProperty val gameId: GameId,
    @JsonProperty val initialPlayerDeck: List<Card>,
    @JsonProperty val initialPlayerHand: List<Card>,
    @JsonProperty val initialOpponentDeck: List<Card>,
    @JsonProperty val initialOpponentHand: List<Card>,
)

data class RoundHasStarted(val gameId: GameId, val player: Player)
data class ManaSlotReceived(val gameId: GameId, val player: Player)
data class CardWasDrawn(val gameId: GameId, val player: Player, val cardId: CardId)
data class ManaWasRefilled(val gameId: GameId, val player: Player)