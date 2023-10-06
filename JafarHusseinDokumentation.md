# Dokumentation: Jafar Hussein

## Gruppen
### namn
- Jafar Hussein

### Beskrivning av projektet
#### Denna projektet är en applikation som skickar data till en kafka topic och sedan läser av datan från topicen och skriver ut i java applicationen, Dessutom så använder applickationen en jpa repository som sparar datan till en mysql databas.

### Vem har gjort vad
#### Jag har utfört arbetet på egen hand på grund av att projektet är ett enskilt projekt.

### Vad ni har gjort
#### Jag har gjort en movie list applikation där en användare kan skapa en lista med filmer som den vill se och spara deni en databas. Sedan kan användaren skriva ut alla filmer som lagts till kafka server.

## Arbetet och dess genomförande
#### Först så behövde vi självklart känna till kafka och hur den fungerar. Sedan så började jag med att skapa en kafka producer som skickar data till en topic. Efter det så skapade jag en kafka consumer som läser av datan från topicen och skriver ut datan i java applikationen. Sedan så skapade jag en jpa repository som sparar datan till en mysql databas. Därefter skapade jag ut clientconsumer som skriver ut från kafka topic.
### Vad som varit svårt
#### Att skapa applikationen var inte svårt, men det som gjorde det svårt var att skapa seperata moduler där jag fick massa med problem med att få dem att fungera tillsammans.
### Beskriv lite olika lösningar du gjort
+ #### Clientconsumer
  + sendToWebApi
    + denna tar in en json objekt som parameter och skickar den till en web api via en http clien.
  + getDataFromKafka
    + denna tar in en string topicName som parameter så att den kan skriva ut datan från topicen.
+ #### inputHandler
    + #### Denna klassen hanterar användarens input så att den blir återanvändbar och för att vi inte har massa scanners i MainMenu klasse.
      + getIntInput
        + denna tar in en nummer input från användaren och har en felhantering som ser till att användaren skriver in ett nummer.
        + getStringInput
          + denna tar in en string input från användaren
      + getLongInput
        + denna tar in en long input från användaren och har en felhantering som ser till att användaren skriver in ett nummer.

### Beskriv något som var besvärligt att få till

### Beskriv om du fått byta lösning och varför i sådana fall


## Slutsatser

### Vad gick bra

### Vad gick dåligt

### Vad har du lärt dig

### Vad hade ni gjort annorlunda om ni gjort om projektet

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
