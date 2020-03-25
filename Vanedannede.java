class Vanedannede extends Legemiddel{
    int styrke;

    public Vanedannede(String n, Double p, Double v, int s){
        super(n,p,v);
        this.styrke = s;
    }

    public int hentVanedannedeStyrke(){
        return styrke;
    }

    @Override
    public String hentType(){
        return "vanedannende";
    }

    @Override
    public int hentStyrke(){
        return styrke;
    }
}
