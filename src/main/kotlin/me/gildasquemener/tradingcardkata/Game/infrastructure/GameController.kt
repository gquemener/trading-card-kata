package me.gildasquemener.tradingcardkata.Game.infrastructure

import me.gildasquemener.tradingcardkata.Game.application.StartNewGame
import me.gildasquemener.tradingcardkata.Game.domain.GameId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController(private val commandGateway: CommandGateway) {

    @PostMapping("/games", produces = [APPLICATION_JSON_VALUE])
    fun create(): NewGameResponse {
        val id = GameId.generate()
        commandGateway.sendAndWait<Object>(StartNewGame(id))

        return NewGameResponse(id.toString())
    }
}

class NewGameResponse (val id: String) {
    constructor() : this("") {
    }
}