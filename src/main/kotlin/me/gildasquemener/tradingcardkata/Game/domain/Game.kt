package me.gildasquemener.tradingcardkata.Game.domain

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
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
    constructor(cmd: StartNewGame, dealer: CardDealer) {
        AggregateLifecycle.apply(GameHasStarted(
            cmd.id,
            dealer.initialPlayerDeck(),
            dealer.initialComputerDeck()
        ))
    }
    
    @EventSourcingHandler
    fun on(event: GameHasStarted) {
        this.id = GameId.fromString(event.gameId);
    }
}