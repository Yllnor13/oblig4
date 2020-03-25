abstract class Legemiddel{
    String navn;
    Double pris;
    Double virkestoff;
    public int Id;
    public static int teller = 0;

    public Legemiddel(String n, Double p, Double v){
        navn = n;
        pris = p;
        virkestoff = v;
        Id = teller;
        teller++;
    }

    public int hentId(){
        return Id;
    }

    public String hentNavn(){
        return navn;
    }

    public Double hentPris(){
        return pris;
    }

    public double hentVirkestoff(){
        return virkestoff;
    }

    public void settNyPris(double nn){
        pris = nn;
    }

    public String toString(){
        return("ID: " + Id + ", navn: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff);
    }

    abstract public String hentType();

    abstract public int hentStyrke();
}
