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

    public static void liksomLes(){ //skulle bli brukt for aa teste noe annet, la bare til det som var i filen manuelt, men bruker ikke metoden i filen naa
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

        try{
            Resept r1 =l1.skrivHvitResept(lm2, p2, 7);
            resepter.leggTil(r1);
        }
        catch(UlovligUtskrift e){
            System.out.println("skriv riktig");
        }
        try{
            Resept r2 = l4.skrivBlaaResept(lm3, p3, 1000);
            resepter.leggTil(r2);
        }
        catch(UlovligUtskrift e){
            System.out.println("skriv riktig");
        }
        try{
            Resept r3 = l3.skrivMilitaerResept(lm1, p1, 12);
            resepter.leggTil(r3);
        }
        catch(UlovligUtskrift e){
            System.out.println("skriv riktig");
        }
        try{
            Resept r4 = l2.skrivPResept(lm4, p3);
            resepter.leggTil(r4);
        }
        catch(UlovligUtskrift e){
            System.out.println("skriv riktig");
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
                        svar2 = brukerInput.nextLine();
                        for(Pasient j : pasienter){
                            int svarid = Integer.parseInt(svar2);
                            if(j.hentId() == svarid){
                                nypas = j;
                                System.out.println("fant pasient");
                                System.out.println("velg hva slags resept du vil lage");
                                System.out.println("skriv det foerste tallet om du vil lage...\n 1. Hvit resept \n 2. Blaa resept \n 3. Militaerresept \n 4. P-resept");
                                String svar3 = " ";
                                svar3 = brukerInput.nextLine();
                                if(svar3.equals("1")){
                                    System.out.println("Du valgte aa lage Hvit resept");
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
                                                System.out.println("Du har naa laget en ny resept");
                                                System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                                            }
                                            catch(UlovligUtskrift e){
                                                System.out.println("det du skrev funket ikke");
                                            }
                                        }
                                    }
                                }
                                else if(svar3.equals("2")){
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
                                    }
                                    
                                }
                                else if(svar3.equals("3")){
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
                                    }
                                }
                                else if(svar3.equals("4")){
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
                                    }
                                }
                            }
                        }
                    }
                }

            }
            else if(bruker.equals("4")){
                System.out.println("du vil lage legemiddel");
                String navn = " ";
                double pris = 0;
                double virkestoff = 0;
                int hvaslagsleg = 0;
                System.out.println("skriv navnet til legemiddelet");
                navn = brukerInput.nextLine();
                System.out.println("skriv prisen til legemiddelet (i tall)");
                pris = brukerInput.nextInt();
                System.out.println("skriv styrken til legemiddelet (i double)");
                virkestoff = brukerInput.nextDouble();
                Legemiddel nyleg = null;
                System.out.println("velg hva slags legemiddel du skal ha \n 1. narkotisk \n 2. vanedannede \n 3. vanlig");
                hvaslagsleg = brukerInput.nextInt();
                if(hvaslagsleg == 1){
                    int styrke = 0;
                    System.out.println("du valgte aa lage en narkotisk legemiddel \n skriv styrken");
                    styrke = brukerInput.nextInt();
                    nyleg = new Narkotisk(navn, pris, virkestoff, styrke);
                    legemidler.leggTil(nyleg);
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                }
                else if(hvaslagsleg == 2){
                    int styrke = 0;
                    System.out.println("du valgte aa lage en vanedannede legemiddel \n skriv styrken");
                    styrke = brukerInput.nextInt();
                    nyleg = new Vanedannede(navn, pris, virkestoff, styrke);
                    legemidler.leggTil(nyleg);
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                }
                else if(hvaslagsleg == 3){
                    System.out.println("du valgte aa lage en vanlig legemiddel");
                    nyleg = new Vanlig(navn, pris, virkestoff);
                    legemidler.leggTil(nyleg);
                    System.out.println("Du har naa laget et nytt legemiddel");
                    System.out.println("tast inn q, trykk enter, saa trykker du enter igjen for aa gaa til hovedmenyen");
                }
                else if(hvaslagsleg != 1 || hvaslagsleg != 2 || hvaslagsleg != 3){
                    System.out.println("det du skrev ble ikke gjenkjent");
                }

            }
            else if(!bruker.equals("q")){
                System.out.println("det du skrev ble ikke gjenkjent");
            }
        }
    }
}