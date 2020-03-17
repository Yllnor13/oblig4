public class HvitResept extends Resept{
    public HvitResept(Legemiddel lm, Lege l, Pasient pasient, int r){
        super(lm, l, pasient, r);
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
