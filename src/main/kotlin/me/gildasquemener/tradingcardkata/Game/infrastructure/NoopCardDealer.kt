package me.gildasquemener.tradingcardkata.Game.infrastructure

import me.gildasquemener.tradingcardkata.Game.domain.Card
import me.gildasquemener.tradingcardkata.Game.domain.CardDealer
import org.springframework.stereotype.Component

@Component
class NoopCardDealer : CardDealer {
    override fun initialPlayerDeck(): List<Card> = listOf()

    override fun initialComputerDeck(): List<Card> = listOf()
}