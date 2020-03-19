import java.util.*;
import java.io.*;

public class LegeSystem{
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    

    public static void main (String[] args){
        File tekst = new File("innlesing.txt");
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
            String[] lestinn = lest.split(" ");
            if(lestinn[1].compareTo("Pasienter") == 0){

            }
        }
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
}