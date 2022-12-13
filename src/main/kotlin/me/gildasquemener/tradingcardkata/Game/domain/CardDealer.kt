package me.gildasquemener.tradingcardkata.Game.domain

interface CardDealer {
    fun initialPlayerDeck(): List<Card>;
    fun initialComputerDeck(): List<Card>;
}