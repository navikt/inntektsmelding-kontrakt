package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class AvsenderSystem @JsonCreator constructor(
    @JsonProperty("navn")
    val navn: String? = null,
    @JsonProperty("versjon")
    val versjon: String? = null,
)
