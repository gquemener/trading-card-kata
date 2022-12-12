package me.gildasquemener.tradingcardkata.Game.domain

import java.util.UUID

data class GameId private constructor(
    private val value: String
) {
    companion object {
        fun generate() = GameId(UUID.randomUUID().toString())
        fun fromString(id: String): GameId = GameId(UUID.fromString(id).toString())
    }

    override fun toString(): String {
        return value
    }
}
