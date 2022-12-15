package me.gildasquemener.tradingcardkata.Game.domain

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.Player.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Game {
    @AggregateIdentifier
    private lateinit var id: GameId

    constructor() {
    }

    @CommandHandler
    constructor(cmd: StartNewGame, dealer: DeckDealer) {
        val (playerHand, playerDeck) = dealer.deck(HUMAN).draw(3);
        val (opponentHand, opponentDeck) = dealer.deck(COMPUTER).draw(3);

        AggregateLifecycle.apply(GameHasStarted(
            cmd.id,
            playerDeck.cards,
            playerHand,
            opponentDeck.cards,
            opponentHand
        ))
    }
    
    @EventSourcingHandler
    fun on(event: GameHasStarted) {
        this.id = GameId.fromString(event.gameId);
    }
}