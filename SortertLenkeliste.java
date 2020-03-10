
class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> implements Liste<T>{

  @Override //overskriver metode
  public void leggTil(T x){

    if(start == null) { //hvis lista er tom
      super.leggTil(x);// bruk super sin metode og la start peker på første objekt

    } else if(stoerrelse() == 1) { //ellers hvis lista har ett objekt
      if(hent(0).compareTo(x) < 0) { //hvis ny objekt er større enn første objekt
        super.leggTil(x); //legger ny objekt i slutten av lista
      } else {
        super.leggTil(0,x); //her vi bruker vi også supper sin metode og legge ny objekt i bestemte posisjon som er i starten av lista
      }
    } else { //hvis det er flere enn et objekt i lista
      boolean lagtTil = false;
      for(int i = 0; i < stoerrelse(); i++){ //looper igjennom lista
        if(hent(i).compareTo(x) > 0) { // hvis ny objekt er mindre
          super.leggTil(i,x);//satt til der originale objekt er, og andre objekter skyver ett hak lengre bak
          lagtTil = true; //hvis vi går inni else, sier vi at vi har lagt til objekter i lista
          break;
        }
      }
      if(!lagtTil) super.leggTil(x); //sjekker om vi har lagt til objekter i lista etter alle if-sjekke, hvis ikke, legg til slutten av lista
    }
  } //Leggtil



  @Override
  public T fjern() { // største elementet skal tas ut
    if(start == null) throw new UgyldigListeIndeks(0);
    T y = fjern(stoerrelse()-1); //kall på fjern(pos) metode og posisjon settes til siste i lista, slik at vi fjerner siste altså største i lista
    return y;
  }

  @Override
  public void sett(int pos, T x){
    throw new UnsupportedOperationException("Ikke mulig aa sette inn paa posisjon.");
  }

  @Override
  public void leggTil(int pos, T x){
    throw new UnsupportedOperationException("Ikke mulig aa legge til paa posisjon.");
  }

}
