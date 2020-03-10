public class Militaerresepter extends Hvite_resepter{

    public Militaerresepter(Legemiddel lm, Lege l, int p, int r){
        super(lm, l, p, r);
    }

    public double prisAaBetale(){
        return (leg.hentPris() * 0);
    }

    public String toString(){
        return("legemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pasientID: " + pasientID + " reit: " + reit + " pris: " + leg.hentPris() + " betal: " + (leg.hentPris() * 0));
    }
}