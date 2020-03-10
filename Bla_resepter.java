public class Bla_resepter extends Resepter{
    double betal = 0.25;
    
    public Bla_resepter(Legemiddel lm, Lege l, int p, int r){
        super(lm, l, p, r);
    }

    public String hentFarge(){
        return "Bla resept";
    }

    public double prosAaBetale(){
        return (leg.hentPris() * betal);
    }

    public String toString(){
        return("legemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pris: " + pasientID + " reit: " + reit + " pris: " + leg.hentPris() + " betal: " + (leg.hentPris() * betal));
    }
}