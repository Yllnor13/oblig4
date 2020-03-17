public class MillitaerResept extends HvitResept{

    public MillitaerResept(Legemiddel lm, Lege l, Pasient pasient, int r){
        super(lm, l, pasient, r);
    }

    public double prisAaBetale(){
        return (leg.hentPris() * 0);
    }

    public String toString(){
        return("legemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pasientID: " + pasientID + " reit: " + reit + " pris: " + leg.hentPris() + " betal: " + (leg.hentPris() * 0));
    }
}
