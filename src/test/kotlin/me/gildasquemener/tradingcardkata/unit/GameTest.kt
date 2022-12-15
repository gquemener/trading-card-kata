package me.gildasquemener.tradingcardkata.unit

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.*
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var fixture: AggregateTestFixture<Game>;

    private val initialPlayerDeck = Deck((1..20).map { Card(it) })
    private val initialOpponentDeck = Deck((1..20).map { Card(it) })

    @BeforeEach
    fun setUp() {
        fixture = AggregateTestFixture(Game::class.java);

        fixture.registerInjectableResource(object : DeckDealer {
            override fun deck(player: Player): Deck {
                return when(player) {
                    Player.HUMAN -> initialPlayerDeck
                    Player.COMPUTER -> initialOpponentDeck
                }
            }
        })
    }

    @Test
    fun testStartNewGame() {
        val id = GameId.generate().toString()
        val (playerHand, playerDeck) = initialPlayerDeck.draw(3);
        val (opponentHand, opponentDeck) = initialOpponentDeck.draw(3);

        fixture
            .`when`(StartNewGame(id))
            .expectSuccessfulHandlerExecution()
            .expectEvents(GameHasStarted(
                id,
                playerDeck.cards,
                playerHand,
                opponentDeck.cards,
                opponentHand
            ))
    }
}