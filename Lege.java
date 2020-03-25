public class Lege implements Comparable<Lege> {
  private Lenkeliste<Resept> utskrevedeResepter; //variablen har type Lenkeliste og Lenkeliste skal inneholde Resepter-objekter
  public String navn;
  public Lege(String n){
      utskrevedeResepter = new Lenkeliste<Resept>();
      navn = n;
  }

  public String hentNavn(){
      return navn;
  }

  public String toString(){
      return("navn: " + navn);
  }

  public int hentKontrollID(){
    return 0;
  }

  public int compareTo(Lege legen){
    return navn.compareTo(legen.hentNavn());
  }//metode for å sammenligne objekter direkte


  public Lenkeliste<Resept> hentResepter(){
    return utskrevedeResepter;
  }//returnerer lista av alle utskrevende resepter


  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    HvitResept resept = new HvitResept(legemiddel, this, pasient, reit); //opprett objekt av HvitResept
    if((legemiddel instanceof Narkotisk)  && !(this instanceof Spesialist)){   //hvis legemiddeler er narkotisk og lege ikke er spesialist
      throw new UlovligUtskrift(this, legemiddel); //kast unntak
    } else {
      utskrevedeResepter.leggTil(resept); //legg inn lista av utskrevende resepter
      return resept;
    }
  }//metode HvitResept

  public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    MilitaerResept resept = new MilitaerResept(legemiddel, this, pasient, reit);
    if((legemiddel instanceof Narkotisk)  && !(this instanceof Spesialist)){   //hvis legemiddeler er narkotisk og lege ikke er spesialist
      throw new UlovligUtskrift(this, legemiddel); //kast unntak
    } else {
      utskrevedeResepter.leggTil(resept); //legg inn lista av utskrevende resepter
      return resept;
    }

  }//metode MillitaerResept

  public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
    PResept resept = new PResept(legemiddel, this, pasient); //opprett objekt
    if((legemiddel instanceof Narkotisk)  && !(this instanceof Spesialist)){   //hvis legemiddeler er narkotisk og lege ikke er spesialist
      throw new UlovligUtskrift(this, legemiddel); //kast unntak
    } else {
      utskrevedeResepter.leggTil(resept); //legg inn lista av utskrevende resepter
      return resept;
    }

  }//metode PResept

  public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
    BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit); //opprett objekt
    if((legemiddel instanceof Narkotisk)  && !(this instanceof Spesialist)){   //hvis legemiddeler er narkotisk og lege ikke er spesialist
      throw new UlovligUtskrift(this, legemiddel); //kast unntak
    } else {
      utskrevedeResepter.leggTil(resept); //legg inn lista av utskrevende resepter
      return resept;
    }
  }// metode BlaaResept


}
