package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class AvsenderSystem @JsonCreator constructor(
    @JsonProperty("avsenderSystemNavn")
    val avsenderSystemNavn: String? = null,
    @JsonProperty("avsenderSystemVersjon")
    val avsenderSystemVersjon: String? = null,
)
