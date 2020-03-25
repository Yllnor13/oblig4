public class HvitResept extends Resept{
    public HvitResept(Legemiddel lm, Lege l, Pasient p, int r){
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
        return("\ntype: Hvit Resept" + "\nlegemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pris: " + pasientID + " reit: " + reit + " pris: " + prosAaBetale());
    }

    @Override
    public String resType(){
        return "hvit";
    }
}
