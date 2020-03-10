public class Lege{
    String navn;
    public Lege(String n){
        navn = n;
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        return("navn: " + navn);
    }
}