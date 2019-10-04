package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.Pattern


data class Inntektsmelding @JsonCreator constructor(

        /** UUID Generert av inntektsmelding mottak*/
        @JsonProperty("inntektsmeldingId")
        val inntektsmeldingId: String,

        /** Arbeidstakers fødselsnummer/dnr  */
        @Pattern(regexp = "[0-9]{11}")
        @JsonProperty("arbeidstakerFnr")
        val arbeidstakerFnr: String,

        /** Arbeidstakers aktørId */
        @Pattern(regexp = "[0-9]{13}")
        @JsonProperty("arbeidstakerAktorId")
        val arbeidstakerAktorId: String,

        /** Virksomhetsnummer for den virksomheten arbeidstaker er knyttet til (har arbeidsforhold hos)
         * Denne skal ha verdi hvis arbeidsgivertype er virksomhet */
        @Pattern(regexp = "[0-9]{9}")
        @JsonProperty("virksomhetsnummer")
        val virksomhetsnummer: String? = null,

        /** Arbeidsgivers fødselsnummer/dnr
         * Denne skal ha verdi hvis arbeidsgiver er en privatperson */
        @Pattern(regexp = "[0-9]{11}")
        @JsonProperty("arbeidsgiverFnr")
        val arbeidsgiverFnr: String? = null,

        /** Arbeidsgivers aktørId
         * Denne skal ha verdi hvis arbeidsgiver er en privatperson */
        @Pattern(regexp = "[0-9]{13}")
        @JsonProperty("arbeidsgiverAktorId")
        val arbeidsgiverAktorId: String? = null,

        /** Er arbeidsgiver en organisasjon (identifisert med virksomhetsnummer), eller en privatperson (identifisert med fnr/aktørId) */
        @JsonProperty("arbeidsgivertype")
        val arbeidsgivertype: Arbeidsgivertype,

        /** ArbeidsforholdId skal oppgis når en arbeidstaker har flere arbeidsforhold hos den samme virksomheten slik at det
         * må sendes inn flere inntektsmeldinger for en arbeidstaker Det skal benyttes samme arbeidsforholdId som sendes inn
         * til a-ordningen og arbeidstakerregisteret.   */
        @JsonProperty("arbeidsforholdId")
        val arbeidsforholdId: String? = null,

        /** Oppgi inntekt som samsvarer med folketrygdloven § 8-28. Oppgis som månedsbeløp. Beløp med to desimaler.
         * Det skal alltid opplyses full lønn.  */
        @field: JsonSerialize(using = PengeSerialiserer::class)
        @JsonProperty("beregnetInntekt")
        val beregnetInntekt: BigDecimal? = null,

        /** Inneholder opplysninger om refusjon  */
        @JsonProperty("refusjon")
        val refusjon: Refusjon,

        /** Inneholder opplysninger om endring i krav om refusjon i fraværsperioden.  */
        @JsonProperty("endringIRefusjoner")
        val endringIRefusjoner: List<EndringIRefusjon>,

        /** Inneholder opplysninger om opphør av naturalytelse.  */
        @JsonProperty("opphoerAvNaturalytelser")
        val opphoerAvNaturalytelser: List<OpphoerAvNaturalytelse>,

        /** Inneholder opplysninger om gjenopptakelse av naturalytelse  */
        @JsonProperty("gjenopptakelseNaturalytelser")
        val gjenopptakelseNaturalytelser: List<GjenopptakelseNaturalytelse>,

        /** Liste av perioder dekket av arbeidsgiver*/
        @JsonProperty("arbeidsgiverperioder")
        val arbeidsgiverperioder: List<Periode>,

        /** Om inntektsmeldingen har kjente mangler eller anses som å være gyldig */
        @JsonProperty("status")
        val status: Status,

        /** Arkivreferanse fra altinn */
        @JsonProperty("arkivreferanse")
        val arkivreferanse: String,

        /** Liste av perioder med ferie */
        @JsonProperty("ferieperioder")
        val ferieperioder: List<Periode>,

        /** Første fraværsdag */
        @JsonProperty("foersteFravaersdag")
        val foersteFravaersdag: LocalDate,

        /** Når vi mottok inntektsmeldingen fra Altinn */
        @JsonProperty("mottattDato")
        val mottattDato: LocalDateTime
)

class PengeSerialiserer : JsonSerializer<BigDecimal>() {
        @Throws(IOException::class, JsonProcessingException::class)
        override fun serialize(value: BigDecimal, jgen: JsonGenerator, provider: SerializerProvider) {
                // put your desired money style here
                jgen.writeString(value.setScale(2, RoundingMode.HALF_UP).toString())
        }
}
