import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    static void main() {
        Band myband = new Band("DANOISE", 'H');
        Band rivalBand = new Band("The WHO", 'R');

        //My band song list
        myband.addSong(new Song("Where are we?", 146, 70));
        myband.addSong(new Song("AID (Am i dying?)", 82, 71));
        myband.addSong(new Song("From the north to the sout pole",  249, 101));
        myband.addSong(new Song("Ototoxicity", 219, 80));
        myband.addSong(new Song("Flap", 140, 70));

        //Rival songlist
        rivalBand.addSong(new Song("Behind blue eyes", 124, 155));
        rivalBand.addSong(new Song("Won't get fooled again", 230, 212));
        rivalBand.addSong(new Song("My generation", 212, 414));
        rivalBand.addSong(new Song("Who are you", 180, 502));
        rivalBand.addSong(new Song("Pinball wizard", 152, 120));

        //Venues to play at
        Venue publicPark = new Venue("Public park", 250, 150, 1);
        Venue garage = new Venue("Dads garage", 125, 50, 1);
        Venue smallStage = new Venue("Small stage", 300, 200, 1);
        Venue midStage = new Venue("Medium stage", 500, 350, 2);
        Venue digitalConcert = new Venue("Digital concert", 2000, 250, 3);
        Venue festival = new Venue("Festival", 1000, 450, 3);
        Venue city = new Venue("City stage", 2000, 400, 4);
        Venue bigStage = new Venue("Big stage", 4000, 2000, 4);
        Venue stadium = new Venue("Stadium", 10000, 7000, 5);

        //Adding venues to venues arrayLists, to cycle through for going on tour
        //Tilføjer previous arraylist + de nye unlockede lvls
        ArrayList <Venue> tourVenuesLvl1 = new ArrayList<>();
        tourVenuesLvl1.add(publicPark);
        tourVenuesLvl1.add(garage);
        tourVenuesLvl1.add(smallStage);

        ArrayList <Venue> tourVenuesLvl2 = new ArrayList<>(tourVenuesLvl1);
        tourVenuesLvl2.add(midStage);
        ArrayList <Venue> tourVenuesLvl3 = new ArrayList<>(tourVenuesLvl2);
        tourVenuesLvl3.add(digitalConcert);
        tourVenuesLvl3.add(festival);
        ArrayList <Venue> tourVenuesLvl4 = new ArrayList<>(tourVenuesLvl3);
        tourVenuesLvl4.add(city);
        tourVenuesLvl4.add(bigStage);
        ArrayList <Venue> tourVenuesLvl5 = new ArrayList<>(tourVenuesLvl4);
        tourVenuesLvl4.add(stadium);

        //Finished loading different valubles
//Input starts here


        myband.printBandProfile();

        rivalBand.printBandProfile();

        System.out.println("Concert venue");
        myband.playGig(publicPark);
        rivalBand.playGig(publicPark);


myband.goOnTour(tourVenuesLvl1);
rivalBand.goOnTour(tourVenuesLvl1);

        System.out.println("Song release " + myband.getBandName());
      myband.releaseSingle();
        System.out.println("Song release " + rivalBand.getBandName());
      rivalBand.releaseSingle();

      myband.goOnTour(tourVenuesLvl1);
      rivalBand.goOnTour(tourVenuesLvl1);
      myband.battleOfBands(rivalBand, smallStage, 4);



        // Who's winning?
        if (myband.getFans() > rivalBand.getFans()) {
            System.out.println(myband.getBandName() + " is dominating the scene!");
        } else if (myband.getFans() == rivalBand.getFans()) {
            System.out.println("The bands are equal!");
        }else{
            System.out.println(rivalBand.getBandName() + " is the crowd favorite!");
        }


myband.printBandProfile();
        myband.printRepertoire();
        System.out.println();
        rivalBand.printBandProfile();
        rivalBand.printRepertoire();
        System.out.println();

        myband.addXP(2000);
        myband.goOnTour(tourVenuesLvl2);

    }
}



