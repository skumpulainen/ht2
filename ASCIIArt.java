/* Harjoitustyö 2
 *
 * Lausekielinen ohjelmointi II, syksy 2016.
 *
 * Sara Kumpulainen 
 *
 * Ohjelma on tarkoitettu ASCII -grafiikkana esitettyjen kuvien katseluun
 * ja käsittelyyn.
 * Ohjelma lataa käynnistyessään kuvan tekstitiedostosta keskimuistiin. 
 * Kuvaa voidaan katsella kahdessa eri muodossa ja kuvan tiedot voidaan tulostaa näytölle.
 * Tiedot sisältävät kuvan koon ja kuvan eri merkkien lukumäärät. Kuvan muokkaus tapahtuu
 * mediaanisuotimella. Muokkaus perutaan lataamalla kuva uudelleen tiedostosta.
 * Ohjelma voidaan pysäyttää käyttäjän toimesta. 
 *
 */

import java.io.*;

public class ASCIIArt {

   // Kaikki class:n alle tehdyt vakiot näkyväty ohjelman
   // kaikkiin operaatioihin.
   // Luodaan ASCII -merkeistä vakiot jotka näkyvät 
   // kaikkialle ohjelmaan. Jokainen merkki vastaa omaa väriään.
   public static final char MUSTA0 = '#';
   public static final char HARMAA1 = '@';
   public static final char HARMAA2 = '&';
   public static final char HARMAA3 = '$';
   public static final char HARMAA4 = '%';
   public static final char HARMAA5 = 'x';
   public static final char HARMAA6 = '*';
   public static final char HARMAA7 = 'o';
   public static final char HARMAA8 = '|';
   public static final char HARMAA9 = '!';
   public static final char HARMAA10 = ';';
   public static final char HARMAA11 = ':';
   public static final char HARMAA12 = '\'';
   public static final char HARMAA13 = ',';
   public static final char HARMAA14 = '.';
   public static final char VALKOINEN15 = ' ';
   // Luodaan vakiomuotoinen 16 -merkkiä pitkä taukukko
   // joiden alkiot ovat ASCII -merkkien vakiot.
   public static final char[] variTaulukko = 
   { MUSTA0, HARMAA1, HARMAA2, HARMAA3, 
   HARMAA4, HARMAA5, HARMAA6,
   HARMAA7, HARMAA8, HARMAA9,
   HARMAA10, HARMAA11, HARMAA12,
   HARMAA13, HARMAA14, VALKOINEN15};
   
   // Tehdään vakiot käyttäjän antamille syötteille.
   public static final String PRINTA = "printa";
   public static final String PRINTI = "printi";
   public static final String INFO = "info";
   public static final String FILTER = "filter";
   public static final String RESET = "reset";
   public static final String LOPETA = "quit";
   // Vakioidaan myös käyttälle annetut ilmoitukset ja 
   // kysymykset.
   public static final String LOPETUS = "Bye, see you soon.";
   public static final String VIRHE = "Invalid command-line argument!";
   public static final String KYSYMYS = "printa/printi/info/filter [n]/reset/quit?";
   // Vakioidaan myös filtterin vakiokoko, eli jos 
   // käyttäjän syöttää filter -komennon ilman erillistä filtterin
   // kokoa.
   public static final int FILTTERINVAKIO = 3;
   
   // Operaatio tulostaa tervehdystekstin ohjelman käynnistyessä
   public static void tulostaTervehdys () {
      System.out.println("-------------------");
      System.out.println("| A S C I I A r t |");  
      System.out.println("-------------------");      
   }
   // Operaatio laskee rivit tiedostosta ja palauttaa tiedon
   // operaatioon "sijoitaTaulukkoon." Jos tapahtuu virhe, palautetaan
   // negatiivinen arvo.
   public static int laskeKuvanRivit (String tiedosto) {
      // Luodaan muuttuja luku, joka laskee rivit.
      int luku = 0;
      try {
         // Avataan tiedosto lukemista varten sekä luodaan olio.
         FileInputStream syotevirta = new FileInputStream(tiedosto);
         // Liitetään lukija syötevirtaan.
         InputStreamReader lukija = new InputStreamReader(syotevirta);
         // Otetaan käyttöön uusi lukija.
         BufferedReader puskuroituLukija = new BufferedReader(lukija);
         // Käydän tiedosta läpi kunnes kaikki rivit käyty läpi.
         while (puskuroituLukija.ready()) {
            // Luetaan rivi.
            String rivi = puskuroituLukija.readLine();    
            // Päivitetään laskuria jolla lasketaan rivejä.          
            luku++;              
      }
      // Suljetaan lukija.        
      puskuroituLukija.close();
      }
      // Napataan virheet ja annetaan luvulle negatiivinen arvo.
      catch (Exception e) {
         luku = -2;
         return luku;
      }
      // Palautetaan rivien määrä opertaatioon "sijoitaTaulukkoon".
      return luku;
   }
   // Operaatio sijoittaa tiedoston merkit taulukkoon. Operaatio palauttaa 
   // tiedoston merkeistä täytetyn taukon main -operaatioon.
   // Jos taulukkoon sijoittamisessa tapahtuu virhe, palautetaan 
   // taulukko null -arvoisena.
   public static char[][] sijoitaTaulukkoon (String tiedosto) {
      // Operaatio kutsuu "laskeKuvanRivit -operaatiota", joka palauttaa 
      // rivit tähän operaatioon. 
      //int luku = laskeKuvanRivit(tiedosto);
      // Luodaan kaksiuloitteinen taulukko joka alustetaan null -arvoiseksi.
      char[][] asciiTaulukko = null;
      // Luodaan muuttuja rivin pituudelle.
      int pituus = 0;
      // Kutsutaan operaatiota joka laskee rivit(laskeKuvanRivit) jotta saadaan 
      // taulukon rivien määrälle arvo.
      int rivit = laskeKuvanRivit(tiedosto);
      try {  
         // Avataan tiedosto lukemista varten sekä luodaan olio.       
         FileInputStream syotevirta = new FileInputStream(tiedosto);
         // Liitetään lukija syötevirtaan.
         InputStreamReader lukija = new InputStreamReader(syotevirta);
         // Otetaan käyttöön uusi lukija.
         BufferedReader puskuroituLukija = new BufferedReader(lukija);
         // Luodaan muuttuja rivin indeksille.
         int riviInd = 0;
         // Käydään tiedosta läpi kunnes rivit käyty läpi.
         while (puskuroituLukija.ready()) {
            String rivi = puskuroituLukija.readLine();
            // pituus saa arvon joka on rivin pituus.
            pituus = rivi.length();
            // Jos rivin indeksi nolla..
            if ( riviInd == 0) {
               // ... voidaan alustetaa taulukko rivien määrällä ja pituudella.
               asciiTaulukko = new char[rivit][pituus];         
            }
            // Sijoitetaan taulukkoon.
            for (int ind = 0; ind < pituus; ind++) {
               asciiTaulukko[riviInd][ind] = rivi.charAt(ind);              
            }
            // Päivitetään rivin indeksiä.
            riviInd++;         
         }
         // Suljetaan lukija.
         puskuroituLukija.close();
      }
      // Jos löytyy virheitä, napataan ne, ja palautetaan
      // null- arvoinen taulukko.
      catch (Exception e) {
         asciiTaulukko = null;
      }
      // Palautetaan taulukko.
      return asciiTaulukko;
   }
   // Operaatio tulostaa ASCII- merkeillä täytetyn taulukon.
   public static void tulostaTaulukko (String tiedosto, 
   char[][] asciiTaulukko) {                                                      
      // Jos taulukko ei ole tyhjä, voidaan se tulostaa.
      if (asciiTaulukko !=null){
         // Tulostetaan taulukko käyden läpi kaikki merkit.
         for (int rivi = 0; rivi < asciiTaulukko.length; rivi++) {
            for (int sarake = 0; sarake < asciiTaulukko[0].length; sarake++) {
               System.out.print(asciiTaulukko[rivi][sarake]);            
            }
            // Tulostetaan rivinvaihto jokaisen rivin jälkeen.       
            System.out.println();
         }  
      }
   }
   // Operaatio tulostaa numeroilla täytetyn taulukon.
   public static void tulostaNumerot (String tiedosto, char [][] asciiTaulukko) {
      // Jos taulukko ei ole tyhjä, voidaan se tulostaa.
      if (asciiTaulukko !=null){
         // Luodaan sarakkeiden määrälle muuttuja.
         int sarakkeet = asciiTaulukko[0].length;
         // Tulostetaan taulukko käyden läpi kaikki merkit.
         for (int rivi = 0; rivi < asciiTaulukko.length; rivi++) {
            for (int sarake = 0; sarake < asciiTaulukko[0].length; sarake++) {
                // Käydään läpi myös kaikki vakioidun variTaulukon merkit
                // jotta sen indeksiä voidaan vertailla asciiTaulukon merkkeihin.
                for (int i = 0; i < variTaulukko.length; i++) {
                   // Jos asciiTaulukon merkki on sama kuin variTaulukon merkki
                   // vertaillaan jotta voidaan tulostaa sen indeksi numerona..
                   if (asciiTaulukko[rivi][sarake] == variTaulukko[i]) {
                      // ... jos i pienempi kuin 10 ja saavutettu
                      // sarakkeiden määrä, tulostetaan numeron eteen välimerkki.
                      if (i < 10 && (sarake+1) == sarakkeet){
                         System.out.print(" " + i);
                      }
                      // ... jos i pienempi kuin 10 ja ei ole saavutettu
                      // sarakkeiden määrä, tulostetaan numeron eteen ja
                      // sen jälkeen välimerkki.
                      else if (i < 10 && (sarake+1) != sarakkeet){
                         System.out.print(" " + i + " ");
                      }
                      // ... jos i on yhtäsuuri tai suurempi kuin 10
                      // ja on saavutettu sarakkeiden määrä, ei tulosteta välejä.
                      else if (i >= 10 && (sarake+1) == sarakkeet){
                         System.out.print(i);
                      }
                      // ... jos i on yhtäsuuri tai suurempi kuin 10
                      // ja ei ole saavutettu sarakkeiden määrää,
                      // tulostetaan numeron jälkeen väli.
                      else if (i >= 10 && (sarake+1) != sarakkeet){
                         System.out.print(i + " ");
                      }
                   }
               } 
            }
            // Tulostetaan jokaisen rivin jälkeen rivinvaihto.
            System.out.println();                     
         }
      }
   }
   // Operaatio laskee taulukon rivien ja sarakkeiden määrät sekä jokaisen taulukon
   // sisältämän ascii -merkin lukumäärän.
   public static void kerroMerkkienMaarat(char [][]asciiTaulukko) {
      // Luodaan laskettaville riveille muuttuja joka tunnistetaan operaation
      // eri vaiheissa.
      int rivit = 0;
      // Sarakkeiden määrä saadaan suoraan.
      int sarakkeet = asciiTaulukko[0].length;
      // Lähdetään laskemaan, jos taulukolle on tilaa.
      if (asciiTaulukko !=null)  {
         //Käydään läpi kaikki merkit ja taulukko. 
         for (int rivi = 0; rivi < asciiTaulukko.length; rivi++) {
            for (int sarake = 0; sarake < asciiTaulukko[0].length; sarake++) {
            }
            // Päivitetään jokaisen rivin jälkeen rivien laskuria.
            rivit++;          
         } 
         // Tulostetaan rivien ja sarakkeiden määrä.  
         System.out.println(rivit + " x " + sarakkeet);        
         // Lasketaan kaikki ascii-merkit. Ulommaista silmukkaa käydään läpi
         // vakioidun variTaulukon(sisältää ascii -merkit vakiona) pituuteen asti, jotta rivejä tulostetaan oikea
         // määrä.
         for (int i = 0; i < variTaulukko.length; i++) {
            // Nollataan laskuri jokaisen variTaulukon merkin jälkeen.
            int luku = 0;
            // Käydään läpi kaikki asciiTaulukon merkit.
            for (int rivi = 0; rivi < asciiTaulukko.length; rivi++) {
               for (int sarake = 0; sarake < asciiTaulukko[0].length; sarake++) {
                  // Jos asciiTaulukon merkki on sama kuin variTaulukon merkki..
                  if (asciiTaulukko[rivi][sarake] == variTaulukko [i]) {
                     // .. lasketaan merkki ja..
                     luku++;           
                  }
               }
            }
            //.. tulostetaan merkkien määrä jokaisen variTaulukon merkin käymisen jälkeen.
            System.out.println(variTaulukko[i] + " " + luku);                
         }
      }   
   }
   // Operaatio muuntaa char[][] -tyyppisen taulukon int[][] -tyyppiseksi taulukoksi ja palauttaa sen 
   // main -operaatioon. Tarvitaan, jos käyttäjä valitsee jommankumman filter -komennoista.
   public static int [][] laskeNumeroTaulukko (char[][] asciiTaulukko) {
      // Luodaan uusi int -tyyppinen kaksiulotteine taulukko, johon luvut voidaan sijoittaa.
      int[][] filtteriTaulukko = new int [asciiTaulukko.length][asciiTaulukko[0].length];
      // Jos asciiTaulukolle oli tilaa, joidaan alkaa luomaan uutta taulukkoa.
      if (asciiTaulukko != null) {
         // Käydään läpi kaikki asciiTaulukon merkit läpi.   
         for (int rivi = 0; rivi < asciiTaulukko.length; rivi++) {
            for (int sarake = 0; sarake < asciiTaulukko[0].length; sarake++) {
               // Jos asciiTaulukon merkki vastaa vakioidun variTaulukon tiettyä merkkiä
               // voidaan uuteen int-taulukkoon sijoittaa variTaulukon merkin järjestysnumero. 
               if (asciiTaulukko[rivi][sarake] == MUSTA0) {
                  filtteriTaulukko[rivi][sarake] = 0;
               }
               // Käydään kaikki "värit" läpi.
               else if (asciiTaulukko[rivi][sarake] == HARMAA1){
                  filtteriTaulukko[rivi][sarake] = 1;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA2){
                  filtteriTaulukko[rivi][sarake] = 2;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA3){
                  filtteriTaulukko[rivi][sarake] = 3;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA4){
                  filtteriTaulukko[rivi][sarake] = 4;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA5){
                  filtteriTaulukko[rivi][sarake] = 5;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA6){
                  filtteriTaulukko[rivi][sarake] = 6;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA7){
                  filtteriTaulukko[rivi][sarake] = 7;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA8){
                  filtteriTaulukko[rivi][sarake] = 8;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA9){
                  filtteriTaulukko[rivi][sarake] = 9;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA10){
                  filtteriTaulukko[rivi][sarake] = 10;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA11){
                  filtteriTaulukko[rivi][sarake] = 11;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA12){
                  filtteriTaulukko[rivi][sarake] = 12;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA13){
                  filtteriTaulukko[rivi][sarake] = 13;
               }
               else if (asciiTaulukko[rivi][sarake] == HARMAA14){
                  filtteriTaulukko[rivi][sarake] = 14;
               }
               else  {
                  filtteriTaulukko[rivi][sarake] = 15;
               }               
            }
         }
      }
      // Palautetaan uusi int -tyyppinen kaksiulotteinen taulukko
      // main -operaatioon, filter- komentokyselyjen alle.
      return filtteriTaulukko;     
   }
   // Operaatio filtteröi käyttäjän antaman pituuden mukaan int -tyyppisen taulukon. Operaatio 
   // kutsuu operaatiota "lajittele numerot", joka lajittelee filtteri-ikkunan mukaan tehdyn 
   // taulukon numerot pienimmästä suurempaan. "LajitteleNumerot" -operaatiosta palautetaan laskettu
   // mediaani tähän operaatioon, jossa se sijoitetaan uuteen taulukkoon. Taulukko palautetaan
   // main- operaatioon.
   public static int[][] filtteroiTaulukko(int[][] filtteriTaulukko,
   int pituus, char[][] asciiTaulukko) {
      // Luodaan uusi taulukko, johon filtteröidyn taulukon mediaani voidaan sijoittaa.      
      int [][] mediaaniTaulukko = new int [filtteriTaulukko.length][filtteriTaulukko[0].length];
      // Luodaan pienempi taulukko, joka saa kookseen ikkunan koon, eli käyttäjän antaman pituuden.
      int[] ikkuna = new int [pituus*pituus];
      // Jos filtyteriTaulukolla on tilaa..
      if (filtteriTaulukko != null) {
         // .. muutetaan asciiTaulukon merkit numeroiksi sijoitusta varten. 
         // Käydään läpi filtteriTaulukon pituus.
         for (int j = 0; j < filtteriTaulukko.length; j++) {
            for (int h = 0; h < filtteriTaulukko[0].length; h++) {
               for (int t = 0; t < variTaulukko.length; t++) {
                  // Vertaillaan merkkejä variTaulukkoon (vakiot).
                  if (asciiTaulukko[j][h] == variTaulukko[t]) {
                     // Sijoitetaan indeksi int- tyyppiseen taulukkoon.
                     mediaaniTaulukko[j][h] = t;
                  }
               }
            }  
         }
         // Jotta taulukko voidaan filtteröidä oikein, reunat on otettava pois. 
         // Ylimääräiset reunat saadaan pois, kun käyttäjän antama suodattimen koko
         // jaetaan kahdella.        
         int reunanLeveys = pituus/2;
         // Suodatetaan taulukko siten, että rivejä käydään läpi lähtien
         // seuraavasta rivistä joka on reunan jälkeen.       
         for (int rivi = reunanLeveys; rivi < filtteriTaulukko.length - reunanLeveys; rivi++) {
            // Sarakkeet käydään läpi samalla periaatteella.
            for (int sarake = reunanLeveys; sarake < filtteriTaulukko[0].length
            - reunanLeveys; sarake++) {
               // Luodaan indeksilaskuri joka alustetaan nollaksi. Indeksi
               // nollataan aina uuteen sarakkeeseen siirtyessä.           
               int i = 0;
               // Sijoitetaan pienemäm ikkuna-taulukkon indeksiin filtteröidyn taulukon
               // merkki sarakkeen kohdalta.
               for (int rivi1 = rivi - reunanLeveys; rivi1 < rivi + reunanLeveys + 1; rivi1++ ) {
                  for (int sarake1 = sarake - reunanLeveys; sarake1 < sarake + reunanLeveys + 1; sarake1++) {
                     ikkuna[i] = filtteriTaulukko[rivi1][sarake1];
                     // Korotetaan laskuria.
                     i++;
                  }
               }
               // Kutsutaan operaatiota joka lajittelee ikkuna-taulukon numerot pienemmästä
               // suurempaan ja laskee sen mediaanin. Sijoitetaan mediaani uuteen taulukkoon.
               int mediaani = lajitteleNumerot(ikkuna);
               mediaaniTaulukko[rivi][sarake] = mediaani;
            }       
         }       
      }
      // Palautetaan uusi filtteröity taulukko main -operaatioon.
      return mediaaniTaulukko;
   }
   // Operaatio lajittelee ikkuna-taulukon numerot pienemmästä suurimpaan ja
   // laskee mediaanin numeroista. Mediaani palautetaan operaatioon
   // filtteroiTaulukko.
   public static int lajitteleNumerot(int[] ikkuna){
      // Luodaan operaation sisälle taulukon kooksi vakio,
      // joka on ikkunaTaulukon pituus.
      final int taulukonKoko = ikkuna.length;      
      // Luodaan apumuuttuja merkkien paikkojen vaihtamista varten.
      int apu;
      // Luodaan muuttuja mediaanin laskemista varten.
      int katkaistu = ikkuna.length/2;
      // Jos tilaa, voidaan lajitella numerot uuteen järjestykseen.
      if (ikkuna != null) {  
         // Käydään kunnes saavutetaan taulukon koko.
         for (int i = 0; i < taulukonKoko; i++){
            // Toinen indeksi joka päivittyy taulukkoa läpikäydessä.
            for (int ind = i + 1; ind < taulukonKoko; ind++) {
               // Apumuuttuja saa arvon nolla.
               apu = 0;
               // Jos taulukon ensimmäinen p�ivittyvä indeksi on
               // suurempi kuin siitä seuraava..
               if (ikkuna[i] > ikkuna[ind]){
                  // ..annetaan apumuuttujalle ensimmäisen indeksin arvo.
                  apu = ikkuna[i];
                  // Annetaan ensimmäiselle indeksille seuraavan indeksin arvo. 
                  ikkuna[i] = ikkuna[ind];
                  // Toinen päivittyvä indeksi saa apumuuttujan arvon
                  ikkuna[ind] = apu;                  
               }
            }
         }
      } 
      // Mediaani järjestetyn taulukon keskimmäinen luku... 
      int mediaani = ikkuna[katkaistu];
      // joka palautetaan operaatioon "filtteroiTaulukko".
      return mediaani;    
   }
   // Pääoperaatio johon useimpien operaatioiden paluuarvot palautuvat ja joista 
   // niitä kutsutaan.    
   public static void main (String[] args) {
      // Luodaan boolean - tyyppinen "luetaan" lippu, jonka avulla
      // voidaan joko ajaa ohjelmaa tai lopettaa se. Tarkastetaan löytyykö.
      // Alustetaan tosi- arvoiseksi.  
      boolean luetaan = true;
      // // Luodaan tiedostolle muuttuja, joka näkyy main -operatiossa.
      String tiedosto = "";
      // Tulostetaan käyttäjälle tervehdys joka sijaitsee "tulostaTervehdys" -opertaatiossa.
      tulostaTervehdys();
      // Jos käyttäjä on antanyt vähintään yhden komentoriviparametrin voidaan 
      // tarkastaa löytyykö tiedostostoa samasta hekemistosta.
      if (args.length == 1) { 
         tiedosto = args[0];
         // Jos tiedosto ei löydy samasta hakemistosta..
         File apuTiedosto = new File(tiedosto);                
         if (!apuTiedosto.exists()) {
            // Tulostetaan virheilmoitus.      
            System.out.println(VIRHE);
            // Lopetetaan ohjelma.
            System.out.println(LOPETUS);
            luetaan = false;
         }
      }
      // Jos komentoriviparametreja ei ole yhtä..
      else {
         // Tulostetaan virheilmoitus ja lopetetaan ohjelma.
         System.out.println(VIRHE);
         System.out.println(LOPETUS);
         luetaan = false;
      }
      // Luodaan char -tyyppinen kaksiulotteinen taulukko joka kutsuu "sijoitaTaulukkoon"
      // -operaatiota. Taulukko saa paluuarvonaan "sijoitaTaulukkoon" operaatiosta palautuvan
      // taulukon. Parametreiksi annetaan tiedosto. 
      char[][] asciiTaulukko = sijoitaTaulukkoon(tiedosto);
      // Jos virheitä ei ole tapahtunutkomentoriviparametreja annettaessa ja tiedostoa haettaessa..
      while (luetaan) {
         // ... Voidaan käyttäjälle esittää kysymys halutuista toiminnoista.
         System.out.println(KYSYMYS);
         // Pyydetään vastausta.
         String vastaus = In.readString();
         // Luodaan pituus- muuttuja suodattimen koolle, joka näkyy main-operaatiossa.
         int pituus = 0;
         // Jos halutaan tulostaa asciitaulukko..
         if (vastaus.equals (PRINTA)) {
            // Kutsutaan operaatiota joka tulostaa asciiTaulukon.
            tulostaTaulukko(tiedosto, asciiTaulukko);
         }
         // Jos halutaan tulostaa numerotaulukko..
         else if (vastaus.equals (PRINTI)) {
            // Kutsutaan operaatiota joka tulostaa numerotaulukon.
            tulostaNumerot(tiedosto, asciiTaulukko);       
         }
         // Jos halutaan tarkastaa merkkien määrät..
         else if (vastaus.equals (INFO)) {
            // Kutsutaan opertaatiota joka laskee merkkien määrät.
            kerroMerkkienMaarat(asciiTaulukko);     
         }
         // Jos käyttäjä valitsee filter -komennon ilman suodattimen kokoa..
         else if (vastaus.equals(FILTER)) {
            // pituus saa suoraan arvon 3. 
            pituus = FILTTERINVAKIO;
            // Kutsutaan operaatiota "laskeNumeroTaulukko".
            int[][] filtteriTaulukko = laskeNumeroTaulukko(asciiTaulukko);
            // Kutsutaan operaatiota "filtteroiTaulukko":
            int[][] mediaaniTaulukko = filtteroiTaulukko(filtteriTaulukko, pituus, asciiTaulukko);
            // Käydään läpi filtteriTaulukon kaikki merkit ja muutetaan filtteröidyn 
            // numerotaulukon numerot asciimerkeiksi.
            for (int rivi = 0; rivi < filtteriTaulukko.length; rivi++) {
               for (int sarake = 0; sarake < filtteriTaulukko[0].length; sarake++) {
                  // Sijoitetaan variTaulukon merkki asciiTaulukkoon.  
                  asciiTaulukko[rivi][sarake] = variTaulukko[mediaaniTaulukko[rivi][sarake]];           
               }
            }
         }
         // Jos käyttäjä valitsee filter komennon johon hän syöttää suodattimen koon.
         else if (vastaus.startsWith(FILTER)) {
            // Katkotaan käyttäjän antama vastaus. 
            String[] katkottu = vastaus.split(" ");
            // Saadaan viimeinen "kirjain" eli käyttäjän antama numero.
            pituus = Integer.parseInt(katkottu[katkottu.length -1]);
            // Kutsutaan operaatiota "laskeNumeroTaulukko".
            int[][] filtteriTaulukko = laskeNumeroTaulukko(asciiTaulukko);
            // Kutsutaan operaatiota "filtteroiTaulukko":
            int[][] mediaaniTaulukko = filtteroiTaulukko(filtteriTaulukko, pituus, asciiTaulukko);
            // Käydään läpi filtteriTaulukon kaikki merkit ja muutetaan filtteröidyn 
            // numerotaulukon numerot asciimerkeiksi.
            for (int rivi = 0; rivi < filtteriTaulukko.length; rivi++) {
               for (int sarake = 0; sarake < filtteriTaulukko[0].length; sarake++) {
                  // Sijoitetaan variTaulukon merkki asciiTaulukkoon.       
                  asciiTaulukko[rivi][sarake] = variTaulukko[mediaaniTaulukko[rivi][sarake]];  
               }
            }           
         }
         // Jos käyttäjä haluaa resetoida filtteröidyn taulukon arvot, ladataan tiedosto
         // uudestaan taulukkoon ja sijoitetaan arvot. Taulukko palaa ennalleen. 
         else if (vastaus.equals (RESET)) {
            asciiTaulukko = sijoitaTaulukkoon(tiedosto);
         }
         // Jos käyttäjä haluaa lopettaa..
         else if (vastaus.equals (LOPETA)){
            // lippu käännetään
            luetaan = false;
            // Lopetetaan ohjelma.
            System.out.println(LOPETUS);
         }         
      }   
   }
}