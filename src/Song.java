public class Song {
        private String title;
        private int durationSeconds;
        private int streams;


        public Song(String title, int durationSeconds, int streams){
            this.title = title;
            this.durationSeconds = durationSeconds;
            this.streams = streams;
        }


        public String toString(){
            return "Title: " + title + " - duration: " + durationSeconds + " - streams: " + streams;
        }

        public String getTitle() {
            return title;
        }
    }
