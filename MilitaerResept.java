public class MilitaerResept extends HvitResept{

    public MilitaerResept(Legemiddel lm, Lege l, Pasient p, int r){
        super(lm, l, p, r);
    }

    public double prisAaBetale(){
        return (leg.hentPris() * 0);
    }

    public String toString(){
        return("type: Militaer Resept" + "\nlegemiddel: " + leg.hentNavn() + " lege: " + doc.hentNavn() + " pasientID: " + pasientID + " reit: " + reit + " pris: " + leg.hentPris() + " betal: " + (leg.hentPris() * 0));
    }
}
