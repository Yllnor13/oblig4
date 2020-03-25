class Narkotisk extends Legemiddel{
    int styrke;
    public Narkotisk(String n, Double p, Double v, int s){
        super(n,p,v);
        this.styrke = s;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String hentType(){
        return "narkotisk";
    }

    @Override
    public int hentStyrke(){
        return styrke;
    }
}
