import java.util.*;
import java.io.*;

public class LegeSystem{
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    

    public static void main (String[] args){
        File tekst = new File("innlesing.txt");
        liksomLes();
        meny();
    }

    public static void liksomLes(){
        Pasient p1 = new Pasient("Jens Hans Olsen", "11111143521");
        Pasient p2 = new Pasient("Petrolina Swiq", "24120099343");
        Pasient p3 = new Pasient("Sven Svendsen", "10111224244");
        Pasient p4 = new Pasient("Juni Olsen", "21049563451");

        pasienter.leggTil(p1);
        pasienter.leggTil(p2);
        pasienter.leggTil(p3);
        pasienter.leggTil(p4);

        Narkotisk lm1 = new Narkotisk("predizol", 450.00, 75.00, 8);
        Vanedannede lm2 = new Vanedannede("Paralgin Forte", 65.00, 400.00, 5);
        Vanlig lm3 = new Vanlig("Placebo Pianissimo", 10.00, 0.00);
        Vanlig lm4 = new Vanlig("Ibux", 240.00, 200.00);

        legemidler.leggTil(lm1);
        legemidler.leggTil(lm2);
        legemidler.leggTil(lm3);
        legemidler.leggTil(lm4);

        Lege l1 = new Lege("Dr. Cox");
        Lege l2 = new Lege("Dr. Wilson");
        Lege l3 = new Spesialist("Dr. House", 12345);
        Lege l4 = new Lege("Dr. Hillestad Lovold");

        leger.leggTil(l1);
        leger.leggTil(l2);
        leger.leggTil(l3);
        leger.leggTil(l4);
    }

    public static void les(File fil){
        Scanner leser = null;
        try{
            leser = new Scanner(fil);
        }
        catch(FileNotFoundException e){
            System.out.println("ingen fil funnet");
        }

        String lest = leser.nextLine();
        while(leser.hasNextLine()){
            String [] tittel = lest.split(" ");
            if(tittel[1] == "Pasienter"){
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    if(lest.charAt(0) == '#'){
                        break;
                    }
                    String[] info = lest.split(",");
                    Pasient pasient = new Pasient(info[0],info[1]);
                    pasienter.leggTil(pasient);
                }
            }
            else if(tittel[1] == "Legemidler"){
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    if(lest.charAt(0) == '#'){
                        break;
                    }
                    String[] info = lest.split(",");
                    if(info[1] == "narkotisk"){
                        int styrke = Integer.parseInt(info[4]);
                        Double pris = Double.parseDouble(info[2]);
                        Double virkestoff = Double.parseDouble(info[3]);
                        Narkotisk narkotisk = new Narkotisk(info[0], pris, virkestoff, styrke);
                        legemidler.leggTil(narkotisk);
                    }
                    if(info[1] == "vanedannede"){
                        int styrke = Integer.parseInt(info[4]);
                        Double pris = Double.parseDouble(info[2]);
                        Double virkestoff = Double.parseDouble(info[3]);
                        Vanedannede vanedannede = new Vanedannede(info[0], pris, virkestoff, styrke);
                        legemidler.leggTil(vanedannede);
                    }
                    if(info[1] == "vanlig"){
                        Double pris = Double.parseDouble(info[2]);
                        Double virkestoff = Double.parseDouble(info[3]);
                        Vanlig vanlig = new Vanlig(info[0], pris, virkestoff);
                        legemidler.leggTil(vanlig);
                    }
                }
            }
            else if(tittel[1] == "Leger"){
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    if(lest.charAt(0) == '#'){
                        break;
                    }
                    String[] info = lest.split(",");
                    int id = Integer.parseInt(info[1]);
                    if(id > 0){
                        Spesialist spesialist = new Spesialist(info[0], id);
                        leger.leggTil(spesialist);
                    }
                    else{
                        Lege lege = new Lege(info[0]);
                        leger.leggTil(lege);
                    }
                }
            }
            else if(tittel[1] == "Resepter"){
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    String[] info = lest.split(",");
                    Legemiddel nymid = null;
                    Pasient nypas = null;
                    Lege nyleg = null;
                    int middelId = Integer.parseInt(info[0]);
                    int pasId = Integer.parseInt(info[2]);
                    
                    for(Legemiddel i : legemidler){
                        if(i.hentId() == middelId){
                            nymid = i;
                        }
                    }
                    for(Lege i : leger){
                        if(i.hentNavn() == info[1]){
                            nyleg = i;
                        }
                    }
                    for(Pasient i : pasienter){
                        if(i.hentId() == pasId){
                            nypas = i;
                        }
                    }
                    if(info[3] == "hvit"){
                        int reit = Integer.parseInt(info[4]);
                        try{
                            Resept resept = nyleg.skrivHvitResept(nymid, nypas, reit);
                            resepter.leggTil(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                    else if(info[3] == "blaa"){
                        int reit = Integer.parseInt(info[4]);
                        try{
                            Resept resept = nyleg.skrivBlaaResept(nymid, nypas, reit);
                            resepter.leggTil(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                    else if(info[3] == "p"){
                        try{
                            Resept resept = nyleg.skrivPResept(nymid, nypas);
                            resepter.leggTil(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                }
            }
        }
        leser.close();
    }

    public static void meny(){
        String bruker = " ";
        while(!bruker.equals("q")){
            System.out.println("meny!");
            System.out.println("her er kommandoene...");
            System.out.println("Tast 1 om du vil vise all informasjon (pasienter, leger, legemidler, resepter) (E3)");
            System.out.println("Tast 2 om du vil opprette ny element i systemet (E4)");
            System.out.println("Tast 3 om du vil bruke en resept til en pasient (E5)");
            System.out.println("Tast 4 om du vil skrive ut forskjellige former for statistikk (E6)");
            System.out.println("Tast 5 om du vil skrive ut all data til en fin (E8)");
            System.out.println("tast Q om du vil avslutte (quitte) programmet");

            Scanner brukerInput = new Scanner(System.in);
            bruker = brukerInput.nextLine();

            if(bruker.equals("1")){
                System.out.println("du tastet 1");
                oversikt();
                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(bruker.equals("2")){
                System.out.println("du tastet 2");
                lagNy();
                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(bruker.equals("3")){
                System.out.println("du tastet 3");

                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(bruker.equals("4")){
                System.out.println("du tastet 4");

                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(bruker.equals("5")){
                System.out.println("du tastet 5");

                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(!bruker.equals("q")){
                System.out.println("det du skrev ble ikke gjenkjent");

                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            
        }
    }

    public static void oversikt(){
        System.out.println("leger...");
        leger.skrivUt();
        System.out.println("resepter...");
        resepter.skrivUt();
        System.out.println("pasienter...");
        pasienter.skrivUt();
        System.out.println("legemidler...");
        legemidler.skrivUt();
    }

    public static void lagNy(){
        System.out.println("Hva vil du gjoere? \n 1. ny lege? \n 2. ny pasient? \n 3. ny legemiddel?\n 4. ny resept?\n q. dra tilbake?");
        String bruker = " ";
        while(!bruker.equals("q")){
            Scanner brukerInput = new Scanner(System.in);
            bruker = brukerInput.nextLine();

            if(bruker.equals("1")){
                System.out.println("Du valgte å lage ny lege\nskriv legens navn");
                String svar1 = " ";
                String svar2 = " ";
                svar1 = brukerInput.nextLine();
                System.out.println("legg til kontrollId (0 om det ikke er en)");
                svar2 = brukerInput.nextLine();
                int kontroll = Integer.parseInt(svar2);
                if(kontroll >= 0){
                    if(kontroll == 0){
                        Lege lege = new Lege(svar1);
                        leger.leggTil(lege);
                    }
                else{
                        Spesialist spesialist = new Spesialist(svar1, kontroll);
                        leger.leggTil(spesialist);
                    }
                }
            }
            else if(bruker.equals("2")){

            }
            else if(bruker.equals("3")){
            
            }
            else if(bruker.equals("4")){

            }
            else if(!bruker.equals("q")){
                System.out.println("det du skrev ble ikke gjenkjent");
            }
        }
    }
}