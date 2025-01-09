Inntektsmelding Kontrakt
================

Intern kontrakt for inntektsmelding internt i sykepenger 
- brukes som format fra spinosaurus og ut til konsumenter (Spleis, Flex og HAG-oppslag)
- Spinosaurus oversetter innkommende Simba-format og XML-altinn-kontraktformat (nav-altinn-inntektsmelding/src/main/xsd/Inntektsmelding20181211.xsd)
til no.nav.inntektsmeldingkontrakt og videresender dette på Aiven-topic til Spleis og Flex.
- Dette formatet brukes også når HAG-bro gjør oppslag til Spinosaurus (henter / sjekker ekstern referanse)

# Komme i gang

Prosjektet tas inn som en dependency for de som skal produsere eller konsumere disse objektene

---

# Henvendelser

Spørsmål knyttet til koden eller prosjektet kan rettes mot:

* Dag Raaum, dag.raaum@nav.no
* Gustav Berggren, gustav.berggren@nav.no
* Morten Byhring, morten.byhring@nav.no

## For NAV-ansatte

Interne henvendelser kan sendes via Slack i kanalen #helse-arbeidsgiver
