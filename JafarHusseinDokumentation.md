# Dokumentation: Jafar Hussein

## Gruppen
### namn
- Jafar Hussein

### Beskrivning av projektet
 Denna projektet är en applikation som skickar data till en kafka topic och sedan läser av datan från topicen och skriver ut i java applicationen, Dessutom så använder applickationen en jpa repository som sparar datan till en mysql databas.

### Vem har gjort vad
 Jag har utfört arbetet på egen hand på grund av att projektet är ett enskilt projekt.

### Vad ni har gjort
#### Jag har gjort en movie list applikation där en användare kan skapa en lista med filmer som den vill se och spara deni en databas. Sedan kan användaren skriva ut alla filmer som lagts till kafka server.

## Arbetet och dess genomförande
 Först så behövde vi självklart känna till kafka och hur den fungerar. Sedan så började jag med att skapa en kafka producer som skickar data till en topic. Efter det så skapade jag en kafka consumer som läser av datan från topicen och skriver ut datan i java applikationen. Sedan så skapade jag en jpa repository som sparar datan till en mysql databas. Därefter skapade jag ut clientconsumer som skriver ut från kafka topic.
### Vad som varit svårt
 Att skapa applikationen var inte svårt, men det som gjorde det svårt var att skapa seperata moduler där jag fick massa med problem med att få dem att fungera tillsammans.
### Beskriv lite olika lösningar du gjort
+ #### inputHandler
    +  Denna klassen hanterar användarens input så att den blir återanvändbar och för att vi inte har massa scanners i MainMenu klasse.
 
       + getIntInput
         + denna tar in en nummer input från användaren och har en felhantering som ser till att användaren skriver in ett nummer.
       + getStringInput
         + denna tar in en string input från användaren
       + getLongInput
         +    denna tar in en long input från användaren och har en felhantering som ser till att användaren skriver in ett nummer.
       
+ #### MainMenu
I denna klassen så bröt jag upp allting i olika metoder för att göra det enklare att läsa och mer oop
  + addMovie
    + denna metoden användes för att spara en film till kafka och databas genom att skicka filmen genom en http request till en web api.
  + viewMovies 
    + denna metoden skriver ut all filmer som finns i kafka topicen.

### JsonMessageController
- Det här klassen är en Spring Boot REST-controller.
- Den hanterar HTTP POST-förfrågningar på "/api/v1/kafka/json/publish".
- När den tar emot en förfrågan skickar den JSON-data till ett Kafka-ämne med hjälp av en JsonProducer.

### JsonProducer
- Det här klassen är en Kafka-producenttjänst.
- Den skickar meddelanden till ett Kafka-ämne med JSON-data.
- Om meddelandet eller något av dess fält är null loggas ett felmeddelande och meddelandet skickas inte.

### JsonConsumer
- Det här klassen är en Kafka-konsumenttjänst.
- Den lyssnar på ett Kafka-ämne med det angivna ämnesnamnet och grupp-ID:t.
- När ett meddelande tas emot sparar den meddelandet i en databas och loggar det mottagna meddelandet.

### ClientConsumer
- Det här klassen är en tjänst som är ansvarig för att skicka filmdata till Web API:en.
- Den konfigurerar en Kafka-konsument med de angivna egenskaperna.
- Den hämtar poster från Kafka-ämnet i JSON-format, bearbetar dem och returnerar en lista med MovieInfo-objekt.
  
### Beskriv något som var besvärligt att få till
Det som var besvärligt att få till var att få alla moduler att fungera tillsammans. Men till slut så fick jag det att fungera genom att lägga till dependencies i pom filerna

### Beskriv om du fått byta lösning och varför i sådana fall
Jag har fått byta flera lösningar på grund av att jag var osäker på vad jag ville ha till mitt program men också på grund av att det var också många bugs i koden.

## Slutsatser
I slutet av dagen så har denna projektet vart roligt och jag har fått en inblick hur det ser ut att jobba med kafka och spring boot och kommer att använda denna projektet för att utveckla vidare.

### Vad gick bra
Det var minimalt med problem, så man kan säga att nästan allting har gått bra när jag satte upp ett tydligt plan.

### Vad gick dåligt
Att fixa moduler var väldigt jobbigt att implementer men till slut så gick det bra

### Vad har du lärt dig
Det jag har lärt mig är hur man sätter upp en kafka cluster och implementera det med java och spring boot samt hur man skapar unit testar kafka.

### Vad hade ni gjort annorlunda om ni gjort om projektet
Jag skulle ha satt upp en tydlig plan över vad jag vill ha för program så att jag vet vad som behövs för att implementera planen.
### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Efter kursen har jag fått kunskap om kafka och hur det används med java för att senare ta med mig till praktikplatsen och implementera det jag har gjort i denna kurs. 
