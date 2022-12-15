package me.gildasquemener.tradingcardkata.Game.application

data class GameState(val id: String, val player: PlayerState) {
    val activePlayer: String? = null;
    val opponent: OpponentState = OpponentState();

    data class PlayerState(val deckSize: Int, val hand: List<Card>) {
        val mana: ManaState = ManaState()
        val health: Int = 30
    }

    class OpponentState {
        val deckSize: Int = 17;
        val handSize: Int = 3;
        val mana: ManaState = ManaState();
        val health: Int = 30;
    }

    class ManaState {
        val available: Int = 0;
        val slots: Int = 0;
    }

    data class Card(val manaCost: Int)
}