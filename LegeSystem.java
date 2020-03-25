import java.util.*; //importerer utils
import java.io.*; //importerer scanner

public class LegeSystem{
    static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>(); //lager sortert lenkeliste for leger siden de skal bli sorterte, alle andre har vanlige lenkelister
    static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
    

    public static void main (String[] args){ //main kode
        File tekst = new File("innlesing.txt");
        les(tekst);
        meny();
    }

    public static void sjekkId(){ //ble brukt for aa teste noe tidligere
        for(Legemiddel i : legemidler){
            System.out.println(i.hentId());
        }
    }

    public static void les(File fil){ // her er den virkelige les filen
        Scanner leser = null;
        try{
            leser = new Scanner(fil);
        }
        catch(FileNotFoundException e){ //kommer en error hvis det ike er en fil
            System.out.println("ingen fil funnet");
        }

        String lest = leser.nextLine(); //string lest er linjer fra leser
        while(leser.hasNextLine()){ //mens det er en linje etter linja som man har naa
            String [] tittel = lest.split(" "); //en string liste med navn tittel som splitter det som var i lest (det som blir splittet er ord mellom mellomrom)
            if(tittel[1].equals("Pasienter")){ //hvis det foerste ordet i tittelen er pasienter
                while(leser.hasNextLine()){ //sjekker neste linje
                    lest = leser.nextLine(); //gjoer lest om til neste linje i leser
                    if(lest.charAt(0) == '#'){ //hvis foerste tegn er #, gaa ut av whileloekka
                        break;
                    }
                    String[] info = lest.split(","); //ny string med info til objekter
                    Pasient pasient = new Pasient(info[0],info[1]); //lag en ny pasient med det som er i foerste og andre plass i info lista
                    pasienter.leggTil(pasient); //legger pasient til lista
                }
            }
            else if(tittel[1].equals("Legemidler")){ //om tittelen er legemiddel saa skal den kjoere koden for aa lage legemiddel
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    if(lest.charAt(0) == '#'){
                        break;
                    }
                    String[] info = lest.split(",");
                    if(info[1].equals("narkotisk")){ //om det er narkotisk legemiddel som blir spesifisert i teksten
                        int styrke = Integer.parseInt(info[4]); //gjoer styrke(gemte posisjon i lista) om til int
                        Double pris = Double.parseDouble(info[2]); //gjoer pris(tredje posisjon i lista) om til double
                        Double virkestoff = Double.parseDouble(info[3]); //gjoer virkestoff(fjerde posisjon i lista) om til doeble
                        Narkotisk narkotisk = new Narkotisk(info[0], pris, virkestoff, styrke); //setter alt sammen og lager en narkotisk legemiddel
                        legemidler.leggTil(narkotisk); //setter den i lista
                    }
                    if(info[1].equals("vanedannende")){ //hvis andre posisjon i info er teksten vanedannede, saa skal vi lage en vanedannede objekt
                        int styrke = Integer.parseInt(info[4]); //gjoer samme som over
                        Double pris = Double.parseDouble(info[2]);
                        Double virkestoff = Double.parseDouble(info[3]);
                        Vanedannede vanedannede = new Vanedannede(info[0], pris, virkestoff, styrke);
                        legemidler.leggTil(vanedannede);
                    }
                    if(info[1].equals("vanlig")){ //sjekker om andre plass i lista er vanlig
                        Double pris = Double.parseDouble(info[2]);
                        Double virkestoff = Double.parseDouble(info[3]);
                        Vanlig vanlig = new Vanlig(info[0], pris, virkestoff);
                        legemidler.leggTil(vanlig); //gjoer det samme som over, bare uten styrke, siden vanlig ikke har det
                    }
                }
            }
            else if(tittel[1].equals("Leger")){ //om ordet etter hashtag er lege
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    if(lest.charAt(0) == '#'){
                        break;
                    }
                    String[] info = lest.split(",");
                    int id = Integer.parseInt(info[1]); //lagrer id infoen som int
                    if(id > 0){ //hvis id er stoerre enn null (altsa, de er en spesialist)
                        Spesialist spesialist = new Spesialist(info[0], id); //lag spesialist
                        leger.leggTil(spesialist); //legg det til i lista
                    }
                    else{ //ellers, lag lege
                        Lege lege = new Lege(info[0]);
                        leger.leggTil(lege);
                    }
                }
            }
            else if(tittel[1].equals("Resepter")){//om ordet etter hashtag er resepter
                while(leser.hasNextLine()){
                    lest = leser.nextLine();
                    String[] info = lest.split(",");
                    Legemiddel nymid = null; //lager et tomt legemilldel
                    Pasient nypas = null; //lager tomt pasient
                    Lege nyleg = null; //lager et tomt lege
                    int middelId = Integer.parseInt(info[0]); //lagrer id til middelet som middelId
                    int pasId = Integer.parseInt(info[2]); //lagrer pasienten sin Id som pasId
                    
                    for(Legemiddel i : legemidler){ //gar gjennom lista med legemidler
                        if(i.hentId() == middelId){ //ser for den som har samme id som middel id
                            nymid = i; //gjoer nymid om til legemiddelet i resepten
                        }
                    }
                    for(Lege i : leger){ //gaar gjennom lista med leger
                        if(i.hentNavn().equals(info[1])){ //ser etter en lege med samme navn som paa resepten
                            nyleg = i; //gjoer nyleg om til legen med samme navn som i resepten
                        }
                    }
                    for(Pasient i : pasienter){ //gaar gjennom pasient lista
                        if(i.hentId() == pasId){ //hvis pasienten i resepten ahr samme id som pasienten funnet
                            nypas = i; //gjoer nypas om til pasienten med samme id som i resepten
                        }
                    }//her skal reseptene bli laget
                    if(info[3].equals("hvit")){ //om  fjerde posisjon i info lista er teksten hvit
                        int reit = Integer.parseInt(info[4]); //gjoer reit om til det som er i femte posisjon
                        try{
                            Resept resept = nyleg.skrivHvitResept(nymid, nypas, reit); //lage resepten med nypas, nymid og reit
                            resepter.leggTil(resept); //legge til resepten i resept lista
                            nypas.leggTilResept(resept); //legge til resepten til pasientens egen reseptliste
                        }
                        catch(UlovligUtskrift e){ //om utskriften er ikke tillat, ta denne erroren
                            System.out.println(e);
                        }
                    }
                    else if(info[3].equals("blaa")){ //gjoer det samme som med hvit resept
                        int reit = Integer.parseInt(info[4]);
                        try{
                            Resept resept = nyleg.skrivBlaaResept(nymid, nypas, reit);
                            resepter.leggTil(resept);
                            nypas.leggTilResept(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                    else if(info[3].equals("millitaer")){ //gjoer det samme som med hvit resept
                        int reit = Integer.parseInt(info[4]);
                        try{
                            Resept resept = nyleg.skrivMilitaerResept(nymid, nypas, reit);
                            resepter.leggTil(resept);
                            nypas.leggTilResept(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                    else if(info[3].equals("p")){ //gjoer nesten det samme som hvit, bare uten reit
                        try{
                            Resept resept = nyleg.skrivPResept(nymid, nypas);
                            resepter.leggTil(resept);
                            nypas.leggTilResept(resept);
                        }
                        catch(UlovligUtskrift e){
                            System.out.println(e);
                        }
                    }
                }
            }
        }
        leser.close(); //lukker leseren
    }

    public static void meny(){ //menyen
        String bruker = " "; //hvor det brukeren skriver blir lagret
        while(!bruker.equals("q")){ //om brukeren taster q saa gaer de ut av menyen
            System.out.println("meny!"); //under er menyen og hva de kan gjoere
            System.out.println("her er kommandoene...");
            System.out.println("Tast 1 om du vil vise all informasjon (pasienter, leger, legemidler, resepter) (E3)");
            System.out.println("Tast 2 om du vil opprette ny element i systemet (E4)");
            System.out.println("Tast 3 om du vil bruke en resept til en pasient (E5)");
            System.out.println("Tast 4 om du vil skrive ut forskjellige former for statistikk (E6)");
            System.out.println("Tast 5 om du vil skrive ut all data til en fil (E8)");
            System.out.println("tast Q om du vil avslutte (quitte) programmet");

            Scanner brukerInput = new Scanner(System.in); //skal scanne etter det brukeren skriver
            bruker = brukerInput.nextLine(); //lagrer det som brukeren skriver

            if(bruker.equals("1")){ //om brukeren skriver 1
                System.out.println("du tastet 1");
                oversikt(); //vis oversikten
                System.out.println("trykk enter for aa draa videre");
                bruker=brukerInput.nextLine();
            }
            else if(bruker.equals("2")){
                System.out.println("du tastet 2");//om brukeren taster 2
                lagNy(); //kjoer lagny koden som sender brukeren til en annen meny
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
            else if(bruker.equals("5")){ //om bruker taster 5, saa skriver den ut dataen paa en ny fil
                System.out.println("du tastet 5");
                skrivUtFil(); //hoppe over til metoden for skrive ut filer.
                System.exit(1); //bruker denne for aa hoppe tilbake

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

    public static void oversikt(){ //oversikten printer ut all info den har i de forskjellige listene
        System.out.println("leger...");
        leger.skrivUt();
        System.out.println("\npasienter...");
        pasienter.skrivUt();
        System.out.println("\nlegemidler...");
        legemidler.skrivUt();
        System.out.println("\nresepter...");
        resepter.skrivUt();
    }

    public static void lagNy(){ //denne metoden skal la brukeren lage ny objekt om de oensker
        System.out.println("Hva vil du gjoere? \n 1. ny lege? \n 2. ny pasient? \n 3. ny resept?\n 4. ny legemiddel?\n q. dra tilbake?");
        String bruker = " ";
        while(!bruker.equals("q")){ //hvis brukeren ikke skriver q skal menyen gaa
            Scanner brukerInput = new Scanner(System.in);
            bruker = brukerInput.nextLine();

            if(bruker.equals("1")){ //hvis bruker taster 1 saa skal de lage lege
                System.out.println("Du valgte aa lage ny lege\nskriv legens navn");
                String svar1 = " ";
                String svar2 = " ";
                svar1 = brukerInput.nextLine(); //gjoer navnet til legen om til svar1
                System.out.println("legg til kontrollId (0 om det ikke er en)");
                svar2 = brukerInput.nextLine(); //gjoer kontrolliden om til svar2
                int kontroll = Integer.parseInt(svar2); //gjoer svar2 om til int
                if(kontroll >= 0){ //ser om brukeren skrev noe som var gyldig
                    if(kontroll == 0){ //om de skrev 0 (altsa, ville lage vanlig lege og ikke spesialist)
                        Lege lege = new Lege(svar1); //lager legen
                        leger.leggTil(lege); //legger dem til i lista
                        System.out.println("lege laget");
                        System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                    }
                    else{ //ellers, gjoer det samme men for spesialist
                        Spesialist spesialist = new Spesialist(svar1, kontroll);
                        leger.leggTil(spesialist);
                        System.out.println("spesialist laget");
                        System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                    }
                }
                else{
                    System.out.println("det du skrev var ikke et gyldig tall");
                }
            }
            else if(bruker.equals("2")){ //om bruker skriver 2 saa lager de pasient
                System.out.println("du vil lage pasient");
                String svar1 = " ";
                String svar2 = " ";
                System.out.println("Skriv navnet til pasienten");
                svar1 = brukerInput.nextLine(); //lagrer det bruker skrev som navn
                System.out.println("skriv inn foedselsnummeret");
                svar2 = brukerInput.nextLine(); //lagrer det brukeren skrev om til foedselsnummeret
                Pasient pasient = new Pasient(svar1, svar2); //lager pasient
                pasienter.leggTil(pasient); //legger pasient til lista
                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
            }
            else if(bruker.equals("3")){ //bruker vil lage resept om de taster 3
                System.out.println("du vil lage resept");
                System.out.println("velg hvilken lege som skal skrive resept");
                leger.skrivUt();
                String svar1 = " ";
                System.out.println("skriv navnet til legen");
                svar1 = brukerInput.nextLine(); //bruker skal skrive navn til legen som skal lage resept
                Pasient nypas = null;
                Lege nyleg = null;
                Legemiddel nymid = null;
                Resept nyrep = null; //lager objekter av ting som skal bli brukt senere
                for(Lege i : leger){ //gar gjennom legelista
                    if(i.hentNavn().equals(svar1)){ //om legen med navnet som er oppgitt er funnet
                        nyleg = i; //gjoer nyleg om til den legen som ble funnet
                        System.out.println("du valgte en lege");
                        String svar2 = " ";
                        System.out.println("velg hvilken pasient som skal faa resept");
                        pasienter.skrivUt();
                        System.out.println("skriv inn pasient Id'en");
                        svar2 = brukerInput.nextLine(); //iden som brukeren skriver skal bli lagret som svar2
                        int svarid = Integer.parseInt(svar2); //gjoer det brukerenskrev om til int
                        for(Pasient j : pasienter){ //gar gjennom lista med pasienter
                            if(j.hentId() == svarid){ //finner pasienten med iden skrevet over
                                nypas = j; //gjoer nypas om til pasienten som er funnet
                                System.out.println("fant pasient");
                                System.out.println("velg hva slags resept du vil lage");
                                System.out.println("skriv det foerste tallet om du vil lage...\n 1. Hvit resept \n 2. Blaa resept \n 3. Militaerresept \n 4. P-resept");
                                String svar3 = " ";
                                svar3 = brukerInput.nextLine();//bruker velge hva slags resept de vil lage med aa taste nummeret relatert i teksten
                                if(svar3.equals("1")){ //sjekker hva brukeren tastet
                                    System.out.println("Du valgte aa lage Hvit resept"); //siden de valgte hvit resept, saa skal det bli lagd hvit resept
                                    System.out.println("velg hvilken legemiddel du vil lage resept med");
                                    legemidler.skrivUt();
                                    String svar4 = " ";
                                    System.out.println("skriv inn id'en til legemiddelet du vil bruke");
                                    svar4 = brukerInput.nextLine(); //brukeren skal skrive inn legemiddelet sin id
                                    int svarlegeid = Integer.parseInt(svar4); //det blir lagret som et tall
                                    for(Legemiddel k : legemidler){ //gaar gjennom lista for legemidelr
                                        if(k.hentId() == svarlegeid){ //om den finner legemiddelet med iden som ble skrevet
                                            System.out.println("fant legemiddel");
                                            nymid = k; //gjoer nymid om til legemiddelet som ble funnet
                                            String svar5 = " ";
                                            System.out.println("skriv hvor mange reiter du vil ha");
                                            svar5 = brukerInput.nextLine(); //skriver inn hvor mange reiter som skal vaere der
                                            int reiter = Integer.parseInt(svar5); //blir lagret som et tall
                                            try{
                                                nyrep = nyleg.skrivHvitResept(nymid, nypas, reiter); //lager resepten
                                                resepter.leggTil(nyrep); //legger den til resepter lista i legeliste
                                                nypas.leggTilResept(nyrep); //legger den til i reseptlista til pasienten
                                                System.out.println("Du har naa laget en ny resept");
                                                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                                            }
                                            catch(UlovligUtskrift e){
                                                System.out.println("det du skrev funket ikke");
                                            }
                                        }
                                    }
                                }
                                else if(svar3.equals("2")){ //gjoer det samme som hvit resept
                                    System.out.println("Du valgte aa lage Blaa resept");
                                    System.out.println("velg hvilken legemiddel du vil lage resept med");
                                    legemidler.skrivUt();
                                    String svar4 = " ";
                                    System.out.println("skriv inn id'en til legemiddelet du vil bruke");
                                    svar4 = brukerInput.nextLine();
                                    int svarlegeid = Integer.parseInt(svar4);
                                    for(Legemiddel k : legemidler){
                                        if(k.hentId() == svarlegeid){
                                            System.out.println("fant legemiddel");
                                            nymid = k;
                                            String svar5 = " ";
                                            System.out.println("skriv hvor mange reiter du vil ha");
                                            svar5 = brukerInput.nextLine();
                                            int reiter = Integer.parseInt(svar5);
                                            try{
                                                nyrep = nyleg.skrivBlaaResept(nymid, nypas, reiter);
                                                resepter.leggTil(nyrep);
                                                nypas.leggTilResept(nyrep);
                                                System.out.println("Du har naa laget en ny resept");
                                                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                                            }
                                            catch(UlovligUtskrift e){
                                                System.out.println("det du skrev funket ikke");
                                            }
                                        }
                                        else{
                                            System.out.println("det var ingen legemiddel med den iden");
                                        }
                                    }
                                    
                                }
                                else if(svar3.equals("3")){ //gjoer det samme som hvit resept
                                    System.out.println("Du valgte aa lage Militaer resept");
                                    System.out.println("velg hvilken legemiddel du vil lage resept med");
                                    legemidler.skrivUt();
                                    String svar4 = " ";
                                    System.out.println("skriv inn id'en til legemiddelet du vil bruke");
                                    svar4 = brukerInput.nextLine();
                                    int svarlegeid = Integer.parseInt(svar4);
                                    for(Legemiddel k : legemidler){
                                        if(k.hentId() == svarlegeid){
                                            System.out.println("fant legemiddel");
                                            nymid = k;
                                            String svar5 = " ";
                                            System.out.println("skriv hvor mange reiter du vil ha");
                                            svar5 = brukerInput.nextLine();
                                            int reiter = Integer.parseInt(svar5);
                                            try{
                                                nyrep = nyleg.skrivHvitResept(nymid, nypas, reiter);
                                                resepter.leggTil(nyrep);
                                                nypas.leggTilResept(nyrep);
                                                System.out.println("Du har naa laget en ny resept");
                                                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                                            }
                                            catch(UlovligUtskrift e){
                                                System.out.println("det du skrev funket ikke");
                                            }
                                        }
                                        else{
                                            System.out.println("det var ingen legemiddel med den iden");
                                        }
                                    }
                                }
                                else if(svar3.equals("4")){ //gjoer det samme som hvit resept men uten reiter
                                    System.out.println("Du valgte aa lage P resept");
                                    System.out.println("velg hvilken legemiddel du vil lage resept med");
                                    legemidler.skrivUt();
                                    String svar4 = " ";
                                    System.out.println("skriv inn id'en til legemiddelet du vil bruke");
                                    svar4 = brukerInput.nextLine();
                                    int svarlegeid = Integer.parseInt(svar4);
                                    for(Legemiddel k : legemidler){
                                        if(k.hentId() == svarlegeid){
                                            System.out.println("fant legemiddel");
                                            nymid = k;
                                            try{
                                                nyrep = nyleg.skrivPResept(nymid, nypas);
                                                resepter.leggTil(nyrep);
                                                nypas.leggTilResept(nyrep);
                                                System.out.println("Du har naa laget en ny resept");
                                                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                                            }
                                            catch(UlovligUtskrift e){
                                                System.out.println("det du skrev funket ikke");
                                            }
                                        }
                                        else{
                                            System.out.println("det var ingen legemiddel med den iden");
                                        }
                                    }
                                }
                                else{
                                    System.out.println("du skrev noe annet enn tillatt");
                                }
                            }
                            else{
                                System.out.println("det du skrev er ikke gjenkjent");
                            }
                        }
                    }
                }

            }
            else if(bruker.equals("4")){
                System.out.println("du vil lage legemiddel"); //brukeren skal lage legemiddel
                String navn = " "; //haugh med objekter som skal bli brukt senere
                double pris = 0;
                double virkestoff = 0;
                int hvaslagsleg = 0;
                System.out.println("skriv navnet til legemiddelet");
                navn = brukerInput.nextLine(); //det brukeren skrev skal bli brukt som navn
                System.out.println("skriv prisen til legemiddelet (i tall)");
                pris = brukerInput.nextInt(); //det brukeren skriver som skal bli brukt som prisen
                System.out.println("skriv styrken til legemiddelet (i double)");
                virkestoff = brukerInput.nextDouble(); //det brukeren skriver som skal bli brukt som virkestoff
                Legemiddel nyleg = null; //tomt legemiddel
                System.out.println("velg hva slags legemiddel du skal ha \n 1. narkotisk \n 2. vanedannede \n 3. vanlig");
                hvaslagsleg = brukerInput.nextInt(); //systemet venter paa det brukeren skriver
                if(hvaslagsleg == 1){ //brukeren vil lage narkotisk legemiddel
                    int styrke = 0;
                    System.out.println("du valgte aa lage en narkotisk legemiddel \n skriv styrken");
                    styrke = brukerInput.nextInt(); //det bruker skriver som styrke blir lagret her
                    nyleg = new Narkotisk(navn, pris, virkestoff, styrke); //ny narkotisk legemiddel lagd med variablene som ble deklarert tidligere
                    legemidler.leggTil(nyleg); //legemiddelet blir lagt til i lista
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen"); //instrukser til brukeren
                }
                else if(hvaslagsleg == 2){ //samme som over
                    int styrke = 0;
                    System.out.println("du valgte aa lage en vanedannede legemiddel \n skriv styrken");
                    styrke = brukerInput.nextInt();
                    nyleg = new Vanedannede(navn, pris, virkestoff, styrke);
                    legemidler.leggTil(nyleg);
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                }
                else if(hvaslagsleg == 3){ //samme som over men uten styrke
                    System.out.println("du valgte aa lage en vanlig legemiddel");
                    nyleg = new Vanlig(navn, pris, virkestoff);
                    legemidler.leggTil(nyleg);
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                }
                else if(hvaslagsleg != 1 || hvaslagsleg != 2 || hvaslagsleg != 3){ //"error" kode
                    System.out.println("det du skrev ble ikke gjenkjent");
                }
            }
            else if(!bruker.equals("q")){ //"error" kode
                System.out.println("det du skrev ble ikke gjenkjent");
            }
        }
    }

    public static void skrivUtFil(){ //metode for aa skrive ut dataen i en ny fil
        PrintWriter nyFil = null; //bruker PrintWrite for aa kunne printe dataen
        try {
            nyFil = new PrintWriter("LegeSystemData.txt"); // lager en ny fil
        } catch (Exception e) { //kjoerer denne hvis hvis det ikke gaar aa lage en fil
            System.out.print("Kan ikke lage denne filen");
            System.exit(1); //hoper tilbake til menyen
        }

        nyFil.println("# Pasienter (navn, fnr)"); //for aa printe pasienter
		for (Pasient p : pasienter) { //gaar gjennom hver element i pasient
            nyFil.println(p.hentNavn() + "," + p.hentFodselsnummer());
        }

        nyFil.println("# Legemidler (navn,type,pris,virkestoff,[styrke])"); //for aa printe legemidler
        for (Legemiddel l : legemidler) { //gaar gjennom hver element i legemiddel
            if(l instanceof Vanlig){ //kjoerer naar det er Vanlig legemiddel
                nyFil.println(l.hentNavn() + "," + l.hentType() + "," + l.hentPris() + "," + l.hentVirkestoff());
            }else{ //denne legger til styrke for legemiddelene som har det
                nyFil.println(l.hentNavn() + "," + l.hentType() + "," + l.hentPris() + "," + l.hentVirkestoff() + "," + l.hentStyrke());
            }
        }

        nyFil.println("# Leger (navn,kontrollID / 0 hvis vanlig lege)"); //for aa printe leger
        for (Lege leg : leger) { //gaar gjennom hver element i lege
            nyFil.println(leg.hentNavn() + "," + leg.hentKontrollID());
        }

        nyFil.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])"); //for aa printe Resepter
        for (Resept res : resepter) { //gaar gjennom hver element i resept
            if(res instanceof PResept){//kjoerer naar det er PResept
		    //skrev det saann at det ser ut som innlesing filen til og med skrive at det en ekstra ,
                nyFil.println(res.hentLegemiddelID() + "," + res.hentLege() + "," + res.hentPasientId().hentId() + "," + res.resType() + ",");
            }else{ //skriver reiter for de som ikke er PResepter
                nyFil.println(res.hentLegemiddelID() + "," + res.hentLege() + "," + res.hentPasientId().hentId() + "," + res.resType() + "," + res.hentReit());
            }
        }

        nyFil.close(); //lukker filen

        System.out.println("\nLegesystem dataen har blitt lagret i LegeSystemData.txt fil");
    }
}
