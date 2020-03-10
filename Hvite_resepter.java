public class Hvite_resepter extends Resepter{
    public Hvite_resepter(Legemiddel lm, Lege l, int p, int r){
        super(lm, l, p, r);
    }

    @Override
    public String hentFarge(){
        return "Hvit resept";
    }

    public double prosAaBetale(){
        return leg.hentPris();
    }

    public String toString(){
        return("legemiddel: " + leg + "lege: " + doc.hentNavn() + "pris: " + pasientID + "reit: " + reit + "pris: " + leg.hentPris());
    }
}