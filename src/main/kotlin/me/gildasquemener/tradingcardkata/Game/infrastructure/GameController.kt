package me.gildasquemener.tradingcardkata.Game.infrastructure

import me.gildasquemener.tradingcardkata.Game.application.FetchGame
import me.gildasquemener.tradingcardkata.Game.application.GameState
import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
class GameController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    @PostMapping("/games", produces = [APPLICATION_JSON_VALUE])
    fun create(): NewGameResponse {
        val id = GameId.generate()
        commandGateway.sendAndWait<Object>(StartNewGame(id.toString()))

        return NewGameResponse(id.toString())
    }

    @GetMapping("/games/{id}", produces = [APPLICATION_JSON_VALUE])
    fun fetch(@PathVariable id: String): GameState {
        return queryGateway.query(
            FetchGame(id),
            ResponseTypes.instanceOf(GameState::class.java)
        ).get();
    }
}

data class NewGameResponse (val id: String) {
    constructor() : this("") {
    }
}