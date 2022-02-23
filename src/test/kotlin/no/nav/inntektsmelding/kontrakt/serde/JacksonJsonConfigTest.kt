package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.databind.ObjectMapper
import no.nav.inntektsmeldingkontrakt.Arbeidsgivertype
import no.nav.inntektsmeldingkontrakt.Inntektsmelding
import no.nav.inntektsmeldingkontrakt.Periode
import no.nav.inntektsmeldingkontrakt.Refusjon
import no.nav.inntektsmeldingkontrakt.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

internal class JacksonJsonConfigTest {

    val objectMapper: ObjectMapper = JacksonJsonConfig.opprettObjectMapper()

    companion object {
        private val foersteJanuar = LocalDate.of(2019,1,1)
        private val andreJanuar = LocalDate.of(2019,1,1)
    }

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
            arbeidsgiverperioder = listOf(Periode(foersteJanuar, andreJanuar)),
            beregnetInntekt = BigDecimal("249000.516"),
            arkivreferanse = "AR123",
            ferieperioder = emptyList(),
            mottattDato = foersteJanuar.atStartOfDay(),
            foersteFravaersdag = foersteJanuar,
            naerRelasjon = true
        )

        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        skalInneholdeTekst(
            serialisertInntektsmelding, """
                        "fom":"2019-01-01"
                    """
        )
        skalInneholdeTekst(
            serialisertInntektsmelding, """
                        "beregnetInntekt":"249000.52"
                    """
        )
        println(serialisertInntektsmelding)

        val deserialsertInntektsmelding =
            objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)
        assertEquals(
            inntektsmelding.arbeidsgiverperioder.get(0),
            deserialsertInntektsmelding.arbeidsgiverperioder.get(0)
        )
        assertEquals(BigDecimal("249000.52"), deserialsertInntektsmelding.beregnetInntekt)
    }

    @Test
    fun kan_deserialisere_og_serialisere_med_ferie() {
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
            arbeidsgiverperioder = listOf(Periode(foersteJanuar, andreJanuar)),
            beregnetInntekt = BigDecimal("249000.516"),
            arkivreferanse = "AR123",
            ferieperioder = listOf(
                Periode(
                    fom = foersteJanuar,
                    tom = foersteJanuar
                ),
                Periode(
                    fom = andreJanuar,
                    tom = andreJanuar
                )
            ),
            mottattDato = foersteJanuar.atStartOfDay(),
            foersteFravaersdag = foersteJanuar,
            naerRelasjon = true
        )

        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)

        val deserialsertInntektsmelding =
            objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)

        assertEquals(2, deserialsertInntektsmelding.ferieperioder.size)
        assertEquals(foersteJanuar, deserialsertInntektsmelding.ferieperioder[0].fom)
        assertEquals(foersteJanuar, deserialsertInntektsmelding.ferieperioder[0].tom)
    }

    private fun skalInneholdeTekst(serialisertInntektsmelding: String, tekst: String) {
        assertTrue(serialisertInntektsmelding.contains(tekst.trimIndent()))
    }

}
