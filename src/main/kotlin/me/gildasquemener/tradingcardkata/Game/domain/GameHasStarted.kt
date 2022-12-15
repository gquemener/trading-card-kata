package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonProperty

class GameHasStarted(
    @JsonProperty("gameId") val gameId: String,
    @JsonProperty("initialPlayerDeck") val initialPlayerDeck: List<Card>,
    @JsonProperty("initialPlayerHand") val initialPlayerHand: List<Card>,
    @JsonProperty("initialOpponentDeck") val initialOpponentDeck: List<Card>,
    @JsonProperty("initialOpponentHand") val initialOpponentHand: List<Card>,
)