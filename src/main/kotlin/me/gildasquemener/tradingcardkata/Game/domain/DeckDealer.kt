package me.gildasquemener.tradingcardkata.Game.domain

interface DeckDealer {
    fun deck(player: Player): Deck;
}