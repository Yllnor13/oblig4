public class Spesialist extends Lege implements Godkjenningsfritak{

    int kontrollID;

    public Spesialist(String n, int i){
        super(n);
        kontrollID = i;
    }

    @Override
    public int hentKontrollID(){
        return kontrollID;
    }

    public String toString(){
        return("navn: " + navn + " kontrollID: " + kontrollID);
    }
}
