public class BlaaResept extends Resept{
    double betal = 0.25;

    public BlaaResept(Legemiddel lm, Lege l, Pasient p, int r){
        super(lm, l, p, r);
    }

    public String hentFarge(){
        return "Bla resept";
    }

    public double prosAaBetale(){
        return (leg.hentPris() * betal);
    }

    public String toString(){
        return("\ntype: Blaa Resept" + "\nlegemiddel: " + leg.hentNavn() + " \nlege: " + doc.hentNavn() + " \npris: " + prosAaBetale() + " \nreit: " + reit + " \npris: " + leg.hentPris() + " betal: " + (leg.hentPris() * betal));
    }

    @Override
    public String resType(){
        return "blaa";
    }
}
