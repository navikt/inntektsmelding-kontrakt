package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.databind.ObjectMapper
import no.nav.inntektsmeldingkontrakt.Arbeidsgivertype
import no.nav.inntektsmeldingkontrakt.AvsenderSystem
import no.nav.inntektsmeldingkontrakt.InntektEndringAarsak
import no.nav.inntektsmeldingkontrakt.Inntektsmelding
import no.nav.inntektsmeldingkontrakt.Periode
import no.nav.inntektsmeldingkontrakt.Refusjon
import no.nav.inntektsmeldingkontrakt.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Month
import java.util.UUID

class JacksonJsonConfigTest {

    val objectMapper: ObjectMapper = JacksonJsonConfig.opprettObjectMapper()

    companion object {
        private val foersteJanuar = LocalDate.of(2019, 1, 1)
        private val andreJanuar = LocalDate.of(2019, 1, 2)
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
            inntektsdato = LocalDate.of(2023, Month.OCTOBER, 13),
            arkivreferanse = "AR123",
            ferieperioder = emptyList(),
            mottattDato = foersteJanuar.atStartOfDay(),
            foersteFravaersdag = foersteJanuar,
            naerRelasjon = true,
            avsenderSystem = AvsenderSystem("AltinnPortal", "1.0"),
            innsenderFulltNavn = "",
            innsenderTelefon = ""
        )

        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"fom":"2019-01-01""""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"beregnetInntekt":"249000.52""""
        )
        println(serialisertInntektsmelding)
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"inntektsdato":"2023-10-13""""
        )
        println(serialisertInntektsmelding)

        val deserialsertInntektsmelding =
            objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)
        assertEquals(
            inntektsmelding.arbeidsgiverperioder.get(0),
            deserialsertInntektsmelding.arbeidsgiverperioder.get(0)
        )
        assertEquals(BigDecimal("249000.52"), deserialsertInntektsmelding.beregnetInntekt)
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"avsenderSystem":{"navn":"AltinnPortal","versjon":"1.0"}"""
        )
    }

    @Test
    fun skal_deserialisere_im_fra_nav_no() {
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
            inntektsdato = LocalDate.of(2023, Month.OCTOBER, 13),
            arkivreferanse = "AR123",
            ferieperioder = emptyList(),
            mottattDato = foersteJanuar.atStartOfDay(),
            foersteFravaersdag = foersteJanuar,
            naerRelasjon = true,
            avsenderSystem = AvsenderSystem("NAV_NO", "1.0"),
            innsenderFulltNavn = "Test Testesen",
            innsenderTelefon = "12345678",
            inntektEndringAarsak = InntektEndringAarsak(
                aarsak = "TestAArsak",
                perioder = listOf(Periode(foersteJanuar, andreJanuar)),
                gjelderFra = foersteJanuar,
                bleKjent = andreJanuar
            ),
            inntektEndringAarsaker = listOf(InntektEndringAarsak(
                aarsak = "TestAArsak",
                perioder = listOf(Periode(foersteJanuar, andreJanuar)),
                gjelderFra = foersteJanuar,
                bleKjent = andreJanuar
            ))
        )

        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"fom":"2019-01-01""""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"beregnetInntekt":"249000.52""""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"inntektsdato":"2023-10-13""""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"avsenderSystem":{"navn":"NAV_NO","versjon":"1.0"}"""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"inntektEndringAarsak":{"aarsak":"TestAArsak","perioder":[{"fom":"2019-01-01","tom":"2019-01-02"}],"gjelderFra":"2019-01-01","bleKjent":"2019-01-02"}"""
        )
        skalInneholdeTekst(
            serialisertInntektsmelding,
            """"inntektEndringAarsaker":[{"aarsak":"TestAArsak","perioder":[{"fom":"2019-01-01","tom":"2019-01-02"}],"gjelderFra":"2019-01-01","bleKjent":"2019-01-02"}]"""
        )
        println(serialisertInntektsmelding)
        val deserialsertInntektsmelding =
            objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)
        assertEquals(
            inntektsmelding.arbeidsgiverperioder.get(0),
            deserialsertInntektsmelding.arbeidsgiverperioder.get(0)
        )
        assertEquals(BigDecimal("249000.52"), deserialsertInntektsmelding.beregnetInntekt)
        assertNull(inntektsmelding.vedtaksperiodeId)
        assertNull(deserialsertInntektsmelding.vedtaksperiodeId)
        val vedtaksperiodeId = UUID.randomUUID()
        val inntektsmeldingMedVedtaksperiode = inntektsmelding.copy(vedtaksperiodeId = vedtaksperiodeId)
        val serialisert = objectMapper.writeValueAsString(inntektsmeldingMedVedtaksperiode)
        skalInneholdeTekst(serialisert, vedtaksperiodeId.toString())
        val deserialsert =
            objectMapper.readValue(serialisert, Inntektsmelding::class.java)
        assertEquals(vedtaksperiodeId, deserialsert.vedtaksperiodeId)
    }

    @Test
    fun kan_deserialisere_og_serialisere_med_ferie() {
        val innsenderFulltNavn = "Det er jeg som er sjefen"
        val innsenderTelefon = "+47 123 45 678"
        val begrunnelseForReduksjonEllerIkkeUtbetalt = "Ferie kanskje?"
        val bruttoUtbetalt = BigDecimal(12345.67)
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
            naerRelasjon = true,
            innsenderFulltNavn = innsenderFulltNavn,
            innsenderTelefon = innsenderTelefon,
            begrunnelseForReduksjonEllerIkkeUtbetalt = begrunnelseForReduksjonEllerIkkeUtbetalt,
            bruttoUtbetalt = bruttoUtbetalt
        )

        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)

        val deserialsertInntektsmelding =
            objectMapper.readValue(serialisertInntektsmelding, Inntektsmelding::class.java)

        assertEquals(2, deserialsertInntektsmelding.ferieperioder.size)
        assertEquals(foersteJanuar, deserialsertInntektsmelding.ferieperioder[0].fom)
        assertEquals(foersteJanuar, deserialsertInntektsmelding.ferieperioder[0].tom)
        assertEquals(innsenderFulltNavn, deserialsertInntektsmelding.innsenderFulltNavn)
        assertEquals(innsenderTelefon, deserialsertInntektsmelding.innsenderTelefon)
        assertEquals(begrunnelseForReduksjonEllerIkkeUtbetalt, deserialsertInntektsmelding.begrunnelseForReduksjonEllerIkkeUtbetalt)
        assertEquals(bruttoUtbetalt.setScale(2, RoundingMode.HALF_UP), deserialsertInntektsmelding.bruttoUtbetalt)
    }

    private fun skalInneholdeTekst(serialisertInntektsmelding: String, tekst: String) {
        assertTrue(serialisertInntektsmelding.contains(tekst.trimIndent()))
    }
}
