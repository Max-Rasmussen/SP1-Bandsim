import java.util.ArrayList;

public class Band {
    private String bandName;
    private char genre;
    private int fans;
    private int maxFans;
    private double cash;
    private int fameLevel;
    private int xp;
    private int lvlUpThreshHold;
    private ArrayList<Song> songList;
    private boolean isActive;
    private ArrayList<Event> events;


    public Band(String bandName, char genre) {
        this.bandName = bandName;
        this.genre = genre;
        this.fans = 1000;
        this.fameLevel = 1;
        this.maxFans = 3750;
        this.xp = 500;
        this.lvlUpThreshHold = 2000;
        this.cash = 500;
        this.songList = new ArrayList<>();
        this.isActive = true;
        this.events = new ArrayList<>();
        loadEvents();
    }


    //Possible events that may occur, cycling through the array with randomnumber
    private void loadEvents(){
        events.add(new Event("Bad", "Producer quit the band! cant produce music!", -800, -900));
        events.add(new Event("Good", "A popular influencer used your music!", +500, +250));
        events.add(new Event("Bad", "Personal tradegy led to scandal on stage!", -300, -150));
        events.add(new Event("Good", "Movie execs purchased your music for a movie!", +300, +1200));
        events.add(new Event("Good", "Music video turned out great! visionary product!", +800, -150));
        events.add(new Event("Bad", "Squirrel jumped on scene and chewed microphone", -150, -230));
        events.add(new Event("Good", "Lightning strike on stage framed main singer perfectly!", +800, -250));
        events.add(new Event("Bad", "Sold out your values for cash", -300, +500));
        events.add(new Event("Good", "Couple got married on stage at concert", +450, +400));
        events.add(new Event("Bad", "Venue burned down in freak pyrotechnincs accident", +100, -700));

    }


    public int getFans() {
        return fans;
    }

    public String getBandName() {
        return bandName;
    }

    public String toString() {
        return bandName + " " + " genre: " + genre + " - fans: " + fans + "/" + maxFans + " - xp: " + xp + " fameLevel: " + fameLevel;
    }

    public boolean isActive() {
        return isActive;
    }

    public void printBandProfile() {
        //Udprint af band
        System.out.println();
        System.out.println("=== BAND PROFILE ===");
        System.out.println("Name: " + bandName);
        System.out.println("Genre: " + genre);
        switch (genre) {
            case 'H':
                System.out.println("Fire fresh hiphop beats from " + bandName);
                break;
            case 'R':
                System.out.println("Rockin hard with " + bandName);
                break;
            case 'M':
                System.out.println("Hardcore metal fans rejoice, " + bandName + " is here");
                break;
            case 'P':
                System.out.println("Energetic pop from " + bandName);
                break;
            case 'J':
                System.out.println("Does anybody even listen to jazz from " + bandName + "?");
                break;
            default:
                System.out.println("Unknown style and beats from " + bandName);
                break;
        }
        System.out.println("Fame level: " + fameLevel);
        System.out.println("Fans: " + fans + "/" + maxFans);
        System.out.println("XP: " + xp + "/" + lvlUpThreshHold);
        System.out.println("Money: " + cash);
        System.out.println("Amount of songs: " + songList.size());
        System.out.println("Active: " + isActive);
        System.out.println(" ");
        getStatus();
    }


    public int playGig(Venue stage) {
        double fansGained = 0;
        int attendence = getRandomAttendence();
        if (attendence > stage.getCapacity()){
            attendence = stage.getCapacity();
        }

        if (fameLevel >= stage.getLvlThreshHold()) {
            double attendencePercent = ((double) attendence / stage.getCapacity()) * 100;
            if (attendencePercent >= 100) {
                attendencePercent = 100;
            }
            System.out.println(" ");
            System.out.println("=== GIG NIGHT ===");
            System.out.println(bandName + " is playing at: " + stage.getName() + " - attendence: " + attendence + "/" + stage.getCapacity());
            //Tjekker hvor fyldt koncerten var, hvis over 80% gir 200 + fans, hvis under 80 gir 50 fans
            double randomvalue = Math.random();
            if (attendencePercent >= 80) {
                fansGained = (attendence * randomvalue) * 2;
                if (fansGained >= (attendence * 0.7)){
                    fansGained = (attendence * 0.7);
                }
                gainFans((int) fansGained);
                System.out.println("Big turnout! " + bandName + " gained " + (int) fansGained + " new fans!!");
                System.out.println("Current " + bandName + "fans: " + fans + "/" + maxFans);
                addXP((int) (fansGained / 1.5));
            } else {
                fansGained = attendence * randomvalue * 1.5;
                if (fansGained >= (attendence * 0.7)){
                    fansGained = (attendence * 0.7);
                }
                gainFans((int) fansGained);
                System.out.println("Normal turnout, " + bandName + " gained " + (int) fansGained + " fans!");
                addXP((int) (fansGained / 1.5));
                System.out.println("Current  fans: " + fans + "/" + maxFans);
            }

            //Gir penge for koncerten
            double payout = stage.getPayoutAmount() * (fameLevel * 0.5);
            cash += payout;
            System.out.println(bandName + " made " + payout + " dollars! Current money: " + cash);
            System.out.println(" ");

            int randomValue = (int) (Math.random() * 4);
            if (randomValue >= 3) {
                randomEvent();
            }
        } else {
            System.out.println("Not famous enough to play at " + stage.getName());
        }
        return (int) fansGained;
    }


    public void randomEvent() {
        //Random event trigger
        System.out.println();
        System.out.println("=== RANDOM EVENT ===");
        int randomValue = (int) (Math.random() * events.size());
        System.out.println(events.get(randomValue));
        cash += events.get(randomValue).getMoneyImpact();
        fans += events.get(randomValue).getFanImpact();
        if (cash < 0) {
            cash = 0;
            losingRelevance();
        }
        if (fans < 0) {
            fans = 0;
            losingRelevance();
        }
        System.out.println("Current fans and money: " + fans + " - " + cash);
        System.out.println();
    }


    public void goOnTour(ArrayList<Venue> venues) {
        System.out.println("=== BIG TOUR! ===");
        for (int i = 0; i < 3; i++) {
            int randomValue = (int) (Math.random() * venues.size());
            playGig(venues.get(randomValue));
        }
        System.out.println("Done with tour");
    }


    private int getRandomAttendence() {
        if (fameLevel < 3) {
            return (int) (Math.random() * 600);
        } else if (fameLevel == 3) {
            return (int) (Math.random() * 2500);
        } else {
            return (int) (Math.random() * 4000);
        }
    }

            /* //Outdated dårlig logic
            if (randomEvent.getType().equals("Bad")) {
                cash -= randomEvent.getMoneyImpact();
                fans -= randomEvent.getFanImpact();
            }else if (randomEvent.getType().equals("Bad Money Good Fans")){
                    cash -= randomEvent.getMoneyImpact();
                    fans += randomEvent.getFanImpact();
                }else if (randomEvent.getType().equals("Good Money Bad Fans")) {
cash += randomEvent.getMoneyImpact();
fans -= randomEvent.getFanImpact();
            }else if (randomEvent.getType().equals("Good")) {
                cash += randomEvent.getMoneyImpact();
                fans += randomEvent.getFanImpact();
                System.out.println();
            }*/

   /* private Event getEvent(int randomValue) {
        return events[randomValue];
    }*/

    public void addEvent(Event event) {
        events.add(event);
        }


    public void removeEvent(Event event) {
    events.remove(event);
    }
    //Gammel random event logik
    //if (eventType == 3) {
    //                int outcome = 500 * fameLevel;
    //                fans += outcome;
    //                System.out.println("Great review!!!  +" + outcome + " fans!");
    //                System.out.println("Current " + bandName + " fans: " + fans + "/" + maxFans);
    //            } else if (eventType >= 1) {
    //
    //                System.out.println("Quiet week, nothing happens.");
    //            } else if (eventType == 0) {
    //
    //                int outcome = 300 * fameLevel;
    //                fans -= outcome;
    //                System.out.println("BIG SCANDAL! -" + outcome + " FANS!");
    //                System.out.println("Current " + bandName + " fans: " + fans + "/" + maxFans);
    //            }

    public void gainFans(int amount) {
        fans += amount;
        if (fans > maxFans) {
            fans = maxFans;
            System.out.println(bandName + " fans are at max! - " + fans + "/" + maxFans);
        }
    }

    public void loseFans(int amount) {
        fans -= amount;
        System.out.println(bandName + " lost " + amount + " fans");
        if (fans <= 0) //printer hvis fans er lavere end 1
        {
            System.out.println("The band has broken up, due to no relevance.");
            isActive = false;
        }
    }


    public boolean losingRelevance() {
        return fans < ((maxFans * 25) / 100) && fans > 1;
    }


    public void earnMoney(double amount) {
        if (amount > 0) {
            cash += amount;
            System.out.println(amount + " + added");
        }
        System.out.println("Invalid, amount must be positive");
    }


    public boolean spendMoney(double amount) {
        if (amount <= cash) {
            cash = -amount;
            return true;
        } else {
            System.out.println("Not enough money");
            return false;
        }
    }


    public void printRepertoire() {
        //For loop for at printe sangene ud
        System.out.println("Repertoire (" + songList.size() + " songs) ");
        for (Song song : songList) {
            System.out.println("- " + song);
        }
    }


    public void getStatus() {

        System.out.println("Fame level: ");
        if (fameLevel == 1) {
            System.out.println("Level 1: “Unknown - Playing in garages”");
        }

        if (fameLevel == 2) {
            System.out.println("Level 2: “Local Hero - Small venues await”");
        }

        if (fameLevel == 3) {
            System.out.println("Level 3: “Rising Star - Festival invitations coming in”");
        }

        if (fameLevel == 4) {
            System.out.println("Level 4: “Mainstream - Arena tours possible”");
        }

        if (fameLevel == 5) {
            System.out.println("Level 5: “Superstar - Stadium glory!”");
        }

        //Level up alert
        System.out.println("=== STATUS CHECK===");
        if (xp >= lvlUpThreshHold) {
            System.out.println("Ready to level up!");
        } else if (xp < lvlUpThreshHold) {
            System.out.println("Not quite ready to level up");
        }
    }


    public void addXP(int amount) {
        if (amount > 0) {
            xp += amount;
            System.out.println("Added " + amount + " of xp!");
            if (xp >= lvlUpThreshHold) {
                levelUp();
            }
        }
    }

    public void levelUp() {
        System.out.println();
        if (xp >= lvlUpThreshHold || fans >= maxFans) {

            xp = 0;
            if (fameLevel == 1) {
                maxFans *= 2;
            }else if (fameLevel == 2){
                maxFans *= (int) 2.5;
            }else{
                maxFans *= 3;
            }
            fameLevel += 1;
            lvlUpThreshHold *= 2;
            System.out.println("Leveled up!");
        } else {
            System.out.println("Not ready to lvl up");
        }
getStatus();
        System.out.println();
    }


    public void addSong(Song s) {
        for (int i = 0; i < songList.size(); i++) {
            if (songList.get(i).getTitle().equals(s.getTitle())) {
                System.out.println("Song already in song list!");
                return;
            }
        }
        songList.add(s);
    }

    public void removeSong(String songName) {
        for (Song song : songList) {
            if (songName.equals(song.getTitle())) {
                songList.remove(song);
                return;
            }
        }
    }


    public boolean hasSong(String songName) {
        for (Song song : songList) {
            if (songName.equals(song.getTitle())) {
                return true;
            }
        }
        return false;
    }


    public void releaseSingle() {

        if (cash >= (700 * fameLevel)) {
            xp += (700 * fameLevel);
            fans += (600 * fameLevel);
            cash -= (700 * fameLevel);
            System.out.println("Song released! Gained 600 fans and 700 XP!");
        } else {
            System.out.println("Not enough money for a release unfortunatly");
        }
    }

    public void battleOfBands(Band opposition, Venue stage, int howManyRounds) {
int oppositionWins = 0;
int myWins = 0;
int totalGained = 0;
int totalOpGained = 0;
        System.out.println(bandName + " VS " + opposition.bandName);
        for (int i = 0 ; i < howManyRounds ; i++){


        int gainedFans = playGig(stage);

        System.out.println();

        int oppositionFans = opposition.playGig(stage);
        System.out.println();

        if (gainedFans < oppositionFans) {
            System.out.println("=!=!=!=!=!=");
            System.out.println(opposition.bandName + " won this round");
            oppositionWins++;
            totalGained += gainedFans;
            totalOpGained += oppositionFans;
        }else if (gainedFans > oppositionFans){
            System.out.println("=!=!=!=!=!=");
                System.out.println(bandName + " won this round");
                myWins++;
                totalGained += gainedFans;
                totalOpGained += oppositionFans;

            }else{
            System.out.println("The bands are equal this round");
            totalGained += gainedFans;
            totalOpGained += oppositionFans;
        }
        }

        if (myWins > oppositionWins){
            System.out.println(bandName + " had the most wins with " + myWins +"! they win the battle!");
        } else if(oppositionWins > myWins){
            System.out.println(opposition.bandName + " had the most wins with " + oppositionWins +"! they win the battle!");
        }else{
            System.out.println("The bands were equal!");
        }
        System.out.println(bandName + " fans gained: " + totalGained);
        System.out.println(opposition.bandName + " fans gained: " + totalOpGained);


        gainFans(totalGained);
        opposition.gainFans(totalOpGained);
        if (totalGained + fans > maxFans){
            System.out.println("However, " + bandName + " is at max fan capacity, so they do not get them");
        }
        if ((totalOpGained + fans > opposition.maxFans)){
            System.out.println("However, " + opposition.bandName + " is at max fan capacity, so they do not get them");

        }
        }
    }



