public class Presepter extends Hvite_resepter{
    
    public Presepter(Legemiddel lm, Lege l, int p){
        super(lm, l, p, 3);
    }

    public double prisAaBetale(){
        if ((leg.hentPris() - 108.0) > 0.0){
            return (leg.hentPris() - 108.0);
        }
        else{
            return 0.0;
        }
    }

    public String toString(){
        return("legemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pasientID: " + pasientID + " reit: " + reit + " pris: " + leg.hentPris());
    }
}