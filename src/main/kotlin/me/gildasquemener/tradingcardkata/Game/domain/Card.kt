package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Card(@JsonProperty val id: CardId, @JsonProperty val manaCost: Int)