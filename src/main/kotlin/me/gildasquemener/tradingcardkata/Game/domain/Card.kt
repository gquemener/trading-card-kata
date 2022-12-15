package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Card(@JsonProperty("manaCost") val manaCost: Int)