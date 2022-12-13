package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonProperty

class GameHasStarted(
    @JsonProperty("gameId") val gameId: String,
    @JsonProperty("initialPlayerDeck") val initialPlayerDeck: List<Card>,
    @JsonProperty("initialComputerDeck") val initialComputerDeck: List<Card>
)