public class Vanedannede extends Legemiddel{
    int styrke;

    public Vanedannede(String n, Double p, Double v, int s){
        super(n,p,v);
        this.styrke = s;
    }

    public int hentVanedannedeStyrke(){
        return styrke;
    }
}