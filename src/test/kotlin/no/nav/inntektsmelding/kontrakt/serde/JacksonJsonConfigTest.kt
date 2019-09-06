package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.databind.ObjectMapper
import no.nav.inntektsmeldingkontrakt.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

internal class JacksonJsonConfigTest {

    val objectMapper: ObjectMapper = JacksonJsonConfig.opprettObjectMapper()

    @Test
    fun skal_deserialisere_dato_og_penger() {
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
                arbeidsgiverperioder = listOf(Periode(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 2))),
                beregnetInntekt = BigDecimal("249000.516"),
                arkivreferanse = "AR123"
        )
        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        skalInneholdeTekst(serialisertInntektsmelding, """
                        "fom":"2019-01-01"
                    """)
        skalInneholdeTekst(serialisertInntektsmelding, """
                        "beregnetInntekt":"249000.52"
                    """)
        println(serialisertInntektsmelding)


        val deserialsertInntektsmelding = objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)
        assertEquals(inntektsmelding.arbeidsgiverperioder.get(0), deserialsertInntektsmelding.arbeidsgiverperioder.get(0))
        assertEquals(BigDecimal("249000.52"), deserialsertInntektsmelding.beregnetInntekt)

    }

    private fun skalInneholdeTekst(serialisertInntektsmelding: String, tekst: String) {
        assertTrue(serialisertInntektsmelding.contains(tekst.trimIndent()))
    }

}