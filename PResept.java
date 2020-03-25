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
        return("\ntype: P Resept" + "\nlegemiddel: " + leg.hentNavn() + "\nlege: " + doc.hentNavn() + "\npasientID: " + pasientID + "\nreit: " + reit + "\npris: " + prisAaBetale());
    }

    @Override
    public String resType(){
        return "p";
    }
}
