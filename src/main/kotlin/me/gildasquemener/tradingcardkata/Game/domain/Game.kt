package me.gildasquemener.tradingcardkata.Game.domain

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.application.StartRound
import me.gildasquemener.tradingcardkata.Game.domain.Player.COMPUTER
import me.gildasquemener.tradingcardkata.Game.domain.Player.HUMAN
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class Game {
    @AggregateIdentifier
    private lateinit var id: GameId

    private lateinit var playerDeck: List<Card>

    constructor()

    @CommandHandler
    constructor(cmd: StartNewGame, dealer: DeckDealer) {
        val (playerHand, playerDeck) = dealer.deck(HUMAN).draw(3)
        val (opponentHand, opponentDeck) = dealer.deck(COMPUTER).draw(3)

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
        this.id = event.gameId
        this.playerDeck = event.initialPlayerDeck
    }

    @CommandHandler
    fun startRound(cmd: StartRound) {
        AggregateLifecycle.apply(RoundHasStarted(this.id, cmd.player))
        AggregateLifecycle.apply(ManaSlotReceived(this.id, cmd.player))

        val card = playerDeck[0];
        AggregateLifecycle.apply(CardWasDrawn(this.id, cmd.player, card.id))

        AggregateLifecycle.apply(ManaWasRefilled(this.id, cmd.player))
    }
}