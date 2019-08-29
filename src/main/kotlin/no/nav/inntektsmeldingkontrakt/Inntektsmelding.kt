package no.nav.inntektsmeldingkontrakt

import javax.validation.constraints.Pattern

data class Inntektsmelding(

        /** TODO forklare hvor denne id'en kommer fra */
        val inntektsmeldingId: String,

        /** Arbeidstakers fødselsnummer/dnr  */
        @Pattern(regexp = "[0-9]{11}")
        val arbeidstakerFnr: String,

        /** Arbeidstakers aktørId */
        @Pattern(regexp = "[0-9]{13}")
        val arbeidstakerAktorId: String,

        /** Virksomhetsnummer for den virksomheten arbeidstaker er knyttet til (har arbeidsforhold hos)
         * Denne skal ha verdi hvis arbeidsgivertype er virksomhet */
        @Pattern(regexp = "[0-9]{9}")
        val virksomhetsnummer: String? = null,

        /** Arbeidsgivers fødselsnummer/dnr
         * Denne skal ha verdi hvis arbeidsgiver er en privatperson */
        @Pattern(regexp = "[0-9]{11}")
        val arbeidsgiverFnr: String? = null,

        /** Arbeidsgivers aktørId
         * Denne skal ha verdi hvis arbeidsgiver er en privatperson */
        @Pattern(regexp = "[0-9]{13}")
        val arbeidsgiverAktorId: String? = null,

        /** Er arbeidsgiver en organisasjon (identifisert med virksomhetsnummer), eller en privatperson (identifisert med fnr/aktørId) */
        val arbeidsgivertype: Arbeidsgivertype,

        /** ArbeidsforholdId skal oppgis når en arbeidstaker har flere arbeidsforhold hos den samme virksomheten slik at det
         * må sendes inn flere inntektsmeldinger for en arbeidstaker Det skal benyttes samme arbeidsforholdId som sendes inn
         * til a-ordningen og arbeidstakerregisteret.   */
        val arbeidsforholdId: String? = null,

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
        val gjenopptakelseNaturalytelser: List<GjenopptakelseNaturalytelse>,

        /** Om inntektsmeldingen har kjente mangler eller anses som å være gyldig */
        val status: Status

)
