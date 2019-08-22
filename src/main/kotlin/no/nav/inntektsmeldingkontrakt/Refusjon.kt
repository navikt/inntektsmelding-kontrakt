package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class Refusjon (

    /** Dersom arbeidsgiver krever refusjon angis refusjonsbeløpet. Beløpet oppgis som månedsbeløp. Refusjonsbeløpet må
     * være likt eller mindre enn den beregnede inntekten per måned. Refusjonsbeløpet skal ikke reduseres i henhold til
     * sykemeldingsgrad eller ved gradert uttak på foreldrepenger, dette gjør NAV. Hvis ytelse er omsorgspenger settes
     * refusjonsbeløpet alltid likt med den beregnede inntekten per måned dersom det kreves refusjon.  */
    val beloepPrMnd: Float? = null,

    /** Dersom refusjonen opphører i stønadsperioden angis siste dag det søkes om refusjon for.  */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val opphoersdato: LocalDate? = null
)