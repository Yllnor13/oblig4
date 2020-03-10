class Stabel<T> extends Lenkeliste<T> implements Liste<T> {

  public void leggPaa(T x){ //metoden legger til objeter i slutten av listen, samme som leggeTil()
    leggTil(x);
  }

  public T taAv(){ //metoden fjerner objekter fra slutten av listen
    if(start == null) throw new UgyldigListeIndeks(0);
    T y = fjern(stoerrelse()-1); //kall på fjern(pos) metode og posisjon settes til siste i lista, siste posisjon er alltid størrelse-1
    return y;
  }

}
