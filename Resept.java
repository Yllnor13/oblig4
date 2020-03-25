public abstract class Resept{
    public static int teller = 0;
    public int Id;
    int reit;
    Lege doc;
    Pasient pasientID;
    Legemiddel leg;

    public Resept(Legemiddel lm, Lege l, Pasient p, int r){
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

    public Legemiddel hentLegemiddel(){
        return leg;
    }

    public String hentLege(){
        return doc.hentNavn();
    }

    public Pasient hentPasientId(){
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

    abstract public String resType();
}
