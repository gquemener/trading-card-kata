package me.gildasquemener.tradingcardkata.Game.domain

import org.junit.jupiter.api.Assertions.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

class DeckTest {
    @Test
    fun `draw cards with no remaining cards in deck`() {
        val card = Card(0)
        val deck = Deck(listOf(card))
        val (drawn, newDeck) = deck.draw(1)

        assertThat(drawn, equalTo(listOf(card)))
        assertThat(newDeck, equalTo(Deck.empty()))
    }

    @Test
    fun `draw cards with remaining cards in deck`() {
        val card1 = Card(1)
        val card2 = Card(2)
        val card3 = Card(3)
        val deck = Deck(listOf(card1, card2, card3))

        val (drawn, newDeck) = deck.draw(2)

        assertThat(drawn, equalTo(listOf(card1, card2)))
        assertThat(newDeck, equalTo(Deck(listOf(card3))))
    }

    @Test
    fun `prevent drawing card from empty deck`() {
        assertThrows(IllegalStateException::class.java) {
            Deck.empty().draw(1)
        }
    }

    @Test
    fun `prevent drawing more cards than available`() {
        assertThrows(IllegalStateException::class.java) {
            Deck(listOf(Card(1))).draw(2)
        }
    }
}