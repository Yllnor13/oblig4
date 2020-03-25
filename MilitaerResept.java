public class MilitaerResept extends HvitResept{

    public MilitaerResept(Legemiddel lm, Lege l, Pasient p, int r){
        super(lm, l, p, r);
    }

    public double prisAaBetale(){
        return (leg.hentPris() * 0);
    }

    public String toString(){
        return("\ntype: Militaer Resept" + "\nlegemiddel: " + leg.hentNavn() + "\nlege: " + doc.hentNavn() + "\npasientID: " + pasientID + "\nreit: " + reit + "\npris: " + prisAaBetale() + "\nbetal: " + (leg.hentPris() * 0));
    }
    
    @Override
    public String resType(){
        return "millitaer";
    }
}
