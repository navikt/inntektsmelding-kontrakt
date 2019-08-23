package no.nav.inntektsmeldingkontrakt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
class InntektsmeldingTest {

    @Test
    fun getVirksomhetsnummer() {
        val r = Refusjon(15f, LocalDate.now())
        assertEquals(15f, r.beloepPrMnd)
    }

}