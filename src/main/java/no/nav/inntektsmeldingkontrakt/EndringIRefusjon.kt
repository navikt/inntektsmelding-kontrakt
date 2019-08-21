package no.nav.inntektsmeldingkontrakt

import java.time.LocalDate

data class EndringIRefusjon (

    /** Dersom arbeidsgiver krever refusjon og refusjonsbeløpet er lavere enn den beregnede månedsinntekten, skal
     * arbeidsgiver opplyse beløpet per måned samt en fra og med dato refusjonsbeløpet gjelder fra. Dette og feltet
     * nedenfor skal brukes i de tilfellene hvor man ønsker å trappe ned refusjonskravet. For ytelsen foreldrepenger må
     * endringsdato være minst en dag etter startdatoForeldrepengeperiode (startdato for foreldrepenger).   */
    val endringsdato: LocalDate? = null,

    /** Dersom arbeidsgiver krever refusjon og refusjonsbeløpet er lavere enn den beregnede månedsinntekten, skal
     * arbeidsgiver opplyse beløpet per måned samt en fra og med dato refusjonsbeløpet gjelder fra. Dette og feltet
     * ovenfor skal brukes i de tilfellene hvor man ønsker å trappe ned refusjonskravet. Merk at dette feltet skal ikke
     * brukes ved endringer knyttet til sykemeldingsgrad. Endring i refusjon er knyttet til for eksempel
     * stillingsendringer.
     */
    val beloep: Float? = null

)