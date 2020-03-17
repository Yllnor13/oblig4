public class PResept extends HvitResept{

    public  PResept(Legemiddel lm, Lege l, Pasient pasient){
        super(lm, l, pasient, 3);
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
