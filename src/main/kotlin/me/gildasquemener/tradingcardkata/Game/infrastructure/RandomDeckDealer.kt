package me.gildasquemener.tradingcardkata.Game.infrastructure

import me.gildasquemener.tradingcardkata.Game.domain.*
import org.springframework.stereotype.Component

@Component
class RandomDeckDealer : DeckDealer {
    override fun deck(player: Player): Deck =
        Deck((1..manaCosts.size).map { Card(CardId.generate(), manaCosts[it - 1]) })

    companion object {
        val manaCosts: List<Int> = listOf(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8);
    }
}