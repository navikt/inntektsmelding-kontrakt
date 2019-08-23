package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.validation.constraints.Pattern

data class Inntektsmelding(

        /** Arbeidstakers fødselsnummer/dnr  */
        @Pattern(regexp = "[0-9]{11}")
        val arbeidstakerFnr: String,
        /** Virksomhetsnummer for den virksomheten arbeidstaker er knyttet til (har arbeidsforhold hos)  */
        @Pattern(regexp = "[0-9]{9}")
        val virksomhetsnummer: String? = null,
        /** Arbeidsgivers fødselsnummer/dnr */
        @Pattern(regexp = "[0-9]{11}")
        val arbeidsgiverFnr: String? = null,
        /** ArbeidsforholdId skal oppgis når en arbeidstaker har flere arbeidsforhold hos den samme virksomheten slik at det
         * må sendes inn flere inntektsmeldinger for en arbeidstaker Det skal benyttes samme arbeidsforholdId som sendes inn
         * til a-ordningen og arbeidstakerregisteret.   */
        val arbeidsforholdId: String? = null,
        /** Felt på sykepenger og svangerskapspenger for å angi første fraværsdag ved alle arbeidsforhold. Feltet trengs for
         * å kunne knytte den enkelte inntektsmeldingen til riktig fravær. Dette er det spesielt behov for dersom den
         * sykmeldte har flere fraværsperioder innenfor et så kort tidsrom at det ikke skal beregnes ny arbeidsgiverperiode.
         * Definisjon av første fraværsdag: Første fraværsdag er den første fraværsdagen i det sammenhengende fraværet som
         * går utover arbeidsgiverperioden. Dersom det er flere fravær i arbeidsgiverperioden, såer det første dagen i det
         * fraværet som går utover arbeidsgiverperioden som skal angis. Dersom arbeidstakeren først har vært fraværende på
         * grunn av arbeidsuførhet utover arbeidsgiverperioden (det er sendt inntektsmelding for dette fraværet), så vært i
         * arbeid en eller flere dager(men ikke så lenge at det er ny arbeidsgiverperiode), for så å bli sykmeldt igjen,
         * skal det sendes ny inntektsmelding og den første fraværsdagen i det siste fraværet oppgis.
         * Feltet skal være obligatorisk ved fravær. Dersom man angir «Beløpet er satt til 0,- da det ikke er fravær i dette
         * arbeidsforholdet» under begrunnelseForReduksjonEllerIkkeUtbetalt, skal  det ikke angis dato.
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val foersteFravaersdag: LocalDate? = null,
        /** Oppgi inntekt som samsvarer med folketrygdloven § 8-28. Oppgis som månedsbeløp. Beløp med to desimaler.
         * Det skal alltid opplyses full lønn.  */
        val beregnetInntekt: Float? = null,
        /** Inneholder opplysninger om refusjon  */
        val refusjon: Refusjon,
        /** Inneholder opplysninger om endring i krav om refusjon i fraværsperioden.  */
        val endringIRefusjoner: List<EndringIRefusjon>,
        /** Inneholder opplysninger om opphør av naturalytelse.  */
        val opphoerAvNaturalytelser: List<OpphoerAvNaturalytelse>,
        /** Inneholder opplysninger om gjenopptakelse av naturalytelse  */
        val gjenopptakelseNaturalytelser: List<GjenopptakelseNaturalytelse>

)
