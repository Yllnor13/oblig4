public class PResept extends HvitResept{

    public  PResept(Legemiddel lm, Lege l, Pasient p){
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
        return("\ntype: P Resept" + "\nlegemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pasientID: " + pasientID + " reit: " + reit + " pris: " + prisAaBetale());
    }
}
