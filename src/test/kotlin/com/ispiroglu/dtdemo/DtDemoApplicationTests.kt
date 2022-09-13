package com.ispiroglu.dtdemo

import com.ispiroglu.dtdemo.domain.model.Student
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.Instant
import java.util.UUID

@SpringBootTest (
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:testdb"
    ]
        )
@AutoConfigureMockMvc
class DtDemoApplicationTests() {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Test
    fun `should return all students`() {
        mockMvc.get("/api/v1/student")
            .andDo { println() }
            .andExpect {
                status { isOk() }
            }
    }


}
