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
                    TODO("Add assertion against response schema")
                }
            }
    }
}