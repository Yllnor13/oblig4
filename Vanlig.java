class Vanlig extends Legemiddel{
    public Vanlig(String n, Double p, Double v){
        super(n,p,v);
    }

    @Override
    public String hentType(){
        return "anlig";
    }

    @Override
    public int hentStyrke(){
        return 0;
    }
}
