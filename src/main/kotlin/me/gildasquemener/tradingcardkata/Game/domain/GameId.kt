package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.util.UUID

data class GameId private constructor(
    @JsonValue private val value: String
) {
    companion object {
        fun generate() = GameId(UUID.randomUUID().toString())
        fun fromString(id: String): GameId = GameId(UUID.fromString(id).toString())
    }

    override fun toString(): String {
        return value
    }
}
