package me.gildasquemener.tradingcardkata.Game.application

import me.gildasquemener.tradingcardkata.Game.domain.GameId
import me.gildasquemener.tradingcardkata.Game.domain.Player
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class StartNewGame(val id: GameId)
data class StartRound(@TargetAggregateIdentifier val id: GameId, val player: Player)