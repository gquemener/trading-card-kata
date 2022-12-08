package me.gildasquemener.tradingcardkata.Game.infrastructure

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class GameController {

    @PostMapping("/games", produces = [APPLICATION_JSON_VALUE])
    fun create(): NewGameResponse {
        return NewGameResponse(UUID.randomUUID().toString())
    }
}

class NewGameResponse (val id: String) {
    constructor() : this("") {
    }
}