package no.nav.inntektsmeldingkontrakt

/** Hvilken type inntektsmelding det er snakk om hvis gammelt format (Fra Altinn med xml kontrakt) Inntektsmelding hvis nytt format (Simba / domene modell json kontrakt) Arbeidsgiveropplysninger */
enum class Format {
    Inntektsmelding,
    Arbeidsgiveropplysninger,
}
