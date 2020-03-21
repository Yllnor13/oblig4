import java.util.*;
import java.io.*;

public class LegeSystem{
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    

    public static void main (String[] args){
        File tekst = new File("innlesing.txt");
        les(tekst);
        meny();
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
            }
            else if(bruker.equals("2")){
                System.out.println("du tastet 2");
            }
            else if(bruker.equals("3")){
                System.out.println("du tastet 3");
            }
            else if(bruker.equals("4")){
                System.out.println("du tastet 4");
            }
            else if(bruker.equals("5")){
                System.out.println("du tastet 5");
            }
            else if(!bruker.equals("q")){
                System.out.println("det du skrev ble ikke gjenkjent");
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
}