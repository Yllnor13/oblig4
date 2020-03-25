class Vanedannende extends Legemiddel{
    int styrke;

    public Vanedannende(String n, Double p, Double v, int s){
        super(n,p,v);
        this.styrke = s;
    }

    public int hentVanedannendeStyrke(){
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
