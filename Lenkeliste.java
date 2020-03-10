
class Lenkeliste<T> implements Liste<T> {
  class Node { //lag indre klasse Node
    Node neste = null;
    T data;
    Node(T x) {
      data = x;
    }
  }//Node

  protected Node start = null; //lag en start peker

  public int stoerrelse() {
    Node p = start;
    int teller = 0;
    while(p != null) { //looper så lenge lista ikke er tom
      p = p.neste; //p ble satt til den neste objektet
      teller++; // når finner første objekt, teller blir 1
    }
    return teller;

  }// stoerrelse()

  public void leggTil(int pos, T x){
    if(pos > stoerrelse() || pos < 0) throw new UgyldigListeIndeks(0); //hvis parameteret posisjon er større enn størrelse eller mindre enn 0
    Node ny = new Node(x);
    Node p = start;
    int teller = 0;
    if(pos == 0){ //hvis det skal legges inn i første posisjon i lista
      ny.neste = start;
      start = ny;
    }
    else {
      while (p != null){ //ellers looper så lenge lista ikke er tom
        if (teller == (pos-1)){ // pekeren stopper en posisjon før parameteret posisjon slik at nye objektet skal bli koblet av forrige samt kobles til neste objekt
          ny.neste = p.neste;
          p.neste = ny;
          break;
        }
        teller++; //oppdaterer teller
        p = p.neste; //oppdaterer p
      }
    }
  } //leggTil(pos)

  public void leggTil(T x) {
    Node ny = new Node(x);
    if(start == null) { //hvis lista er tom
      start = ny;// sett start til ny nodeobejekt og dette skjer bare første gang vi kaller leggTil
    }
    else {
      Node p = start; // opprett en Node p som settes til start
      while(p.neste != null) { //while-løkka looper igjennom alle nodeobjekter fra start og settes p til p sin peker- neste, løkka stopper når p (neste) ikke peker på noe objekter altså når p er null
      p = p.neste;
      }

      p.neste = ny;
    }

  }//Leggtil()


  public void sett(int pos, T x) {
    if(start == null || pos >= stoerrelse() || pos < 0) throw new UgyldigListeIndeks(0);
    Node ny = new Node(x);
    Node p = start;
    int teller = 0;
    if(pos == 0){
      ny.neste = start.neste; //hvis posisjon er 0
      start = ny; //setter start til node ny
    }
    else {
      while (p != null){ //ellers looper så lenge lista ikke er tom
        if (teller == (pos-1)){
          ny.neste = p.neste.neste;
          p.neste = ny;
          break;
        }
        p = p.neste;
        teller++;
      }
    }
}// sett()


  public T hent(int pos){
    if(start == null || pos >= stoerrelse() || pos < 0) throw new UgyldigListeIndeks(0);
    int teller = 0;
    Node p = start;
    T svar = null;
    while(p != null){
      if(teller == pos){ //hvis posisjon stemmer med teller
        svar = p.data; //lagrer p sin data i svar
      }
      p = p.neste;
      teller++;
    }
    return svar;
  } //hent()


  public T fjern(int pos){
    if(start == null || pos >= stoerrelse() || pos < 0) throw new UgyldigListeIndeks(0); //start objekt kan ikke være null, og posisjon kan ikke være større enn størrelser eller er negativt tall
    Node p = start;
    int teller = 0;
    T svar = null;
    if(pos == 0){ //hvis vi skal fjerne den alle første objektet i lista
      svar = start.data; //lagrer først verdi fra første objektet i svar
      start = start.neste; //fjerner første objekt og start peker på andre objekt

    }
    else {
      while(p != null){ //ellers så lenge lista ikke er tom
        if(teller == (pos-1)){ //stopper på forrige posisjon
          svar = p.neste.data;
          p.neste = p.neste.neste;
          break;
        }
        p = p.neste;
        teller++;
      }

    }
    return svar;
  } //fjern(pos)

  public T fjern() {
      if(start == null) throw new UgyldigListeIndeks(0); //Kaster unntak, når listen er tom. Ingenting å fjerne. Start peker altså på null.
      Node y = start; //opprett y for å holde første objekt
      start = start.neste; //start blir nå til sin neste
      return (y.data); //returner y som er fjernet objekt fra starten av listen.


  }//fjern() 
}
