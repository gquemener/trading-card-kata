package me.gildasquemener.tradingcardkata.Game.infrastructure

import me.gildasquemener.tradingcardkata.Game.domain.Player
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class RandomDeckDealerTest {
    @ParameterizedTest
    @EnumSource(Player::class)
    fun `generate a deck of 20 random cards for a player`(player: Player) {
        val deck = RandomDeckDealer().deck(player)

        assertThat(deck.cards.size, equalTo(20));
        assertThat(deck.cards.map { it.manaCost }, equalTo(listOf(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8)))
    }
}