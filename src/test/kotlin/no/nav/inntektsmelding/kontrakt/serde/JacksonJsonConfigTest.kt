package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.databind.ObjectMapper
import no.nav.inntektsmeldingkontrakt.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class JacksonJsonConfigTest {

    val objectMapper: ObjectMapper = JacksonJsonConfig.opprettObjectMapper()

    @Test
    fun skal_deserialisere_dato() {
        val inntektsmelding = Inntektsmelding(
                inntektsmeldingId = "ENLANGIDENTIFIKATOR",
                arbeidstakerFnr = "00000000000",
                arbeidstakerAktorId = "00000000000",
                refusjon = Refusjon(),
                endringIRefusjoner = emptyList(),
                opphoerAvNaturalytelser = emptyList(),
                gjenopptakelseNaturalytelser = emptyList(),
                status = Status.GYLDIG,
                arbeidsgivertype = Arbeidsgivertype.VIRKSOMHET,
                arbeidsgiverperioder = listOf(Periode(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 2)))
        )
        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        assertTrue(serialisertInntektsmelding.contains("""
            fom":"2019-01-01"
        """.trimIndent()))
    }

}