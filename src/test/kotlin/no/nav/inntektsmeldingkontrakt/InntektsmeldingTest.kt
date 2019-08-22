package no.nav.inntektsmeldingkontrakt

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
class InntektsmeldingTest {

    @Test
    fun getVirksomhetsnummer() {
        val r = Refusjon( 15f, LocalDate.now() );
        assertEquals(15f, r.beloepPrMnd)
    }

}