package no.nav.inntektsmeldingkontrakt

import java.time.LocalDate

data class GjenopptakelseNaturalytelse (

        /** Hvis arbeidstaker igjen skulle motta naturalytelsen så skal det oppgis hvilken naturalytelse dette gjelder.   */
    val naturalytelse: Naturalytelse? = null,

        /** Må oppgis dersom naturalytelsestype angis. Fra og med dato arbeidstaker igjen mottar naturalytelsen, dvs. den
     * datoen NAV ikke lengre skal erstatte den bortfalte ytelsen.  */
    val fom: LocalDate? = null,

        /** ???  */
    val beloepPrMnd: Float? = null


)