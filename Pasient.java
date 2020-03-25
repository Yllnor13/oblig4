public class Pasient{ //Del B: Skriv klassen Pasient

   //Deklarerer instansvariabler
  private String navn; //Pasienten har et navn og et fødselsnummer-tekststreng
  private long fodselsnummer;
  private static int ID = 0; //Hver pasient har en unik ID. Ingen pasienter har samme ID.
  private int min_id;
  private Stabel<Resept> reseptListe; //Vi bruker en ​Stabel<Resept>​ til å lagre pasientens resepter

  public Pasient(String nav, long fodselnr){ //Lag konstruktøren til​ Pasient
    navn = nav;
    fodselsnummer = fodselnr;
    min_id = ID;  //Når en ny pasient registreres, skal denne i tillegg få en unik ID
    ID++;       //Derfor skal ID øke med 1 for hver gang en ny pasient blir registrert inn
    reseptListe = new Stabel<Resept>(); //Pasienter har også en liste over reseptene
  }

  //Legge til nye resepter i reseptlisten
  public void leggTilResept(Resept resept){
    reseptListe.leggPaa(resept); //Legg til nye resepter i reseptlisten, ved hjelp av leggPaa() metoden fra Stabel klassen.
  }

  //Siden pasienten ofte vil bruke en resept kort tid etter at den er utskrevet, bruker vi en ​Stabel<Resept>​
  public Stabel<Resept> hentReseptListe(){
    return reseptListe; //Hente ut hele reseptlisten
  }


  //Pasient s​kal ha metodene hentId, hentNavn og hentFodselsnummer ​som returnerer de relevante verdiene
  public int hentId(){
    return min_id;
  }

  public String hentNavn(){
    return navn;
  }

  public long hentFodselsnummer(){
    return fodselsnummer;
  }

  //Overskriv ​toString() ​metoden, slik at du lett kan skrive ut all tilgjengelig informasjon om Pasient
  public String toString(){
    return min_id + ", Pasient-navn: " + navn + ", Foedselsnummer: " + fodselsnummer;
  }
}
