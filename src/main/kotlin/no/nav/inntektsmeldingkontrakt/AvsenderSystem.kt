package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonProperty

data class AvsenderSystem(
    @JsonProperty("navn")
    val navn: String? = null,
    @JsonProperty("versjon")
    val versjon: String? = null,
)
