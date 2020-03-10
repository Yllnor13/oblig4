public abstract class Resepter{
    public static int teller = 0;
    public int Id;
    int reit;
    Lege doc;
    int pasientID;
    Legemiddel leg;

    public Resepter(Legemiddel lm, Lege l, int p, int r){
        reit = r;
        Id = teller;
        teller++;
        doc = l;
        pasientID = p;
        leg = lm;
    }

    public int hentId(){
        return Id;
    }

    public String hentLegemiddel(){
        return leg.hentNavn();
    }

    public String hentLege(){
        return doc.hentNavn();
    }

    public int hentPasientId(){
        return pasientID;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if(reit>0){
            reit -= 1;
            return true;
        }
        return false;
    }

    abstract public String hentFarge();

    abstract public double prosAaBetale();
}