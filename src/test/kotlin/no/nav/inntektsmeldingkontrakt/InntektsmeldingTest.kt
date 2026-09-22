package no.nav.inntektsmeldingkontrakt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class InntektsmeldingTest {

    @Test
    fun getRefusjon() {
        val r = Refusjon(BigDecimal.TEN, LocalDate.now())
        assertEquals(BigDecimal.TEN, r.beloepPrMnd)
    }

    @Test
    fun `refusjonskravGyldigFra skal vaere foerste dag i maaneden tre maaneder foer mottattDato`() {
        val inntektsmelding = lagInntektsmelding(mottattDato = LocalDateTime.of(2019, 5, 17, 10, 0))
        assertEquals(LocalDate.of(2019, 2, 1), inntektsmelding.refusjonskravGyldigFra)
    }

    @Test
    fun `refusjonskravGyldigFra skal haandtere aarsskifte`() {
        val inntektsmelding = lagInntektsmelding(mottattDato = LocalDateTime.of(2020, 1, 15, 10, 0))
        assertEquals(LocalDate.of(2019, 10, 1), inntektsmelding.refusjonskravGyldigFra)
    }

    private fun lagInntektsmelding(mottattDato: LocalDateTime): Inntektsmelding {
        val periode = Periode(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 2))
        return Inntektsmelding(
            inntektsmeldingId = "ENLANGIDENTIFIKATOR",
            arbeidstakerFnr = "00000000000",
            arbeidstakerAktorId = "00000000000",
            innsenderFulltNavn = "",
            innsenderTelefon = "",
            arbeidsgivertype = Arbeidsgivertype.VIRKSOMHET,
            refusjon = Refusjon(),
            endringIRefusjoner = emptyList(),
            opphoerAvNaturalytelser = emptyList(),
            gjenopptakelseNaturalytelser = emptyList(),
            arbeidsgiverperioder = listOf(periode),
            status = Status.GYLDIG,
            arkivreferanse = "AR123",
            ferieperioder = emptyList(),
            foersteFravaersdag = periode.fom,
            mottattDato = mottattDato,
            naerRelasjon = null
        )
    }

}