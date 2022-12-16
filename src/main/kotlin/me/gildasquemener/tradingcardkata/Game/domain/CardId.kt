package me.gildasquemener.tradingcardkata.Game.domain

import com.fasterxml.jackson.annotation.JsonValue
import java.util.UUID

data class CardId private constructor(
    @JsonValue private val value: String
) {
    companion object {
        fun generate() = CardId(UUID.randomUUID().toString())
        fun fromString(id: String): CardId = CardId(UUID.fromString(id).toString())
    }

    override fun toString(): String {
        return value
    }
}