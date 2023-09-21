package no.nav.inntektsmeldingkontrakt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class InntektsmeldingTest {

    @Test
    fun getRefusjon() {
        val r = Refusjon(BigDecimal.TEN, LocalDate.now())
        assertEquals(BigDecimal.TEN, r.beloepPrMnd)
    }

}