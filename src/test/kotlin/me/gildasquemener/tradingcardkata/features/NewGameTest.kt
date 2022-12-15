package me.gildasquemener.tradingcardkata.features

import com.fasterxml.jackson.databind.ObjectMapper
import me.gildasquemener.tradingcardkata.Game.infrastructure.NewGameResponse
import org.hamcrest.core.IsAnything
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
internal class `Starting a new game` {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @Autowired
    private lateinit var objectMapper: ObjectMapper;

    @Test
    fun `should succeed`() {
        val freshGame = mockMvc.post("/games") {
          contentType = MediaType.APPLICATION_JSON
        } .andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                content {
                    jsonPath("$.id", IsAnything<String>())
                }
            }
        } .andReturn().response.contentAsString

        val response = objectMapper.readValue(freshGame, NewGameResponse::class.java)
        mockMvc.get("/games/{id}", response.id)
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("$.id") { value(response.id) }
                    jsonPath("$.activePlayer") { value(null) }
                    jsonPath("$.player.deckSize") { value(17) }
                    jsonPath("$.player.hand.length()") { value(3) }
                    jsonPath("$.player.mana.available") { value(0) }
                    jsonPath("$.player.mana.slots") { value(0) }
                    jsonPath("$.player.health") { value(30) }
                    jsonPath("$.opponent.deckSize") { value(17) }
                    jsonPath("$.opponent.handSize") { value(3) }
                    jsonPath("$.opponent.mana.available") { value(0) }
                    jsonPath("$.opponent.mana.slots") { value(0) }
                    jsonPath("$.opponent.health") { value(30) }
                }
            }
    }
}