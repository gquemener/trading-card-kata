package me.gildasquemener.tradingcardkata.Game.domain

data class Deck(val cards: List<Card>) {
    fun draw(count: Int): Pair<List<Card>, Deck> {
        if (cards.size < count) {
            throw IllegalStateException("Not enough cards in deck")
        }
        val drawn = this.cards.subList(0, count)
        val newDeck = Deck(this.cards.subList(count, this.cards.size))

        return drawn to newDeck;
    }

    companion object {
        fun empty(): Deck = Deck(listOf())
    }
}