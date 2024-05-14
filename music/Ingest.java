package music;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// Ingest the data from the Spotify dataset creates individual Song objects 
// and inserts all the songs into a Fibonacci Heap
public class Ingest {
    private SongHashMap songHashMap;
    public FibonacciHeap heap = new FibonacciHeap();
    public int[] priorities;
    public String[] preferences;

    // Ingest the data from the Spotify dataset creates individual Song objects 
    // and inserts all the songs into a Fibonacci Heap
    public Ingest(String filePath, String[] preferences, int[] priorities) {
        songHashMap = new SongHashMap(); // Initialize the class field instead of a local variable
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
          
                if (attributes.length < 21) { // Ensure all expected fields are present
                    continue; // Skip this line if not all fields are present
                }

                try {
                    // For each song, get all song attributes
                    String id = attributes[1];
                    String[] artists = attributes[2].split(";");
                    String album = attributes[3];
                    String name = attributes[4];
                    int popularity = Integer.parseInt(attributes[5]);
                    double duration = Double.parseDouble(attributes[6]);
                    boolean explicit = Boolean.parseBoolean(attributes[7]);
                    double dance = Double.parseDouble(attributes[8]);
                    double energy = Double.parseDouble(attributes[9]);
                    double loudness = Double.parseDouble(attributes[11]);
                    double speechiness = Double.parseDouble(attributes[13]);
                    double accoustic = Double.parseDouble(attributes[14]);
                    double instrumental = Double.parseDouble(attributes[15]);
                    double liveness = Double.parseDouble(attributes[16]);
                    double valence = Double.parseDouble(attributes[17]);
                    double tempo = Double.parseDouble(attributes[18]);
                    String genre = attributes[20];

                    // Create song objects for each song
                    Song song = new Song(id, artists, album, name,  popularity, duration, explicit, dance, energy, loudness, speechiness, accoustic, instrumental, liveness, valence, tempo, genre);

                    // Use score method to calculate a score for each song
                    song.score = score(song, preferences, priorities);

                    heap.insert(song.score, song, song.get_id());


                } catch (NumberFormatException e) { // Check if there is faulty data entries in the dataset
                    System.out.println("Error parsing number from file: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Calculate score for a given song based on preferences and priorities
    public int score(Song song, String [] preferences, int[] priorities) {
        // Set the intial score to the maximum possible valaue
        int finalScore = Integer.MAX_VALUE;
        // Decrease the score calculated based on preferences from the 
        // intial max score so that the higher ranked songs would have
        // a lower final score

        // Genre Score
        if (song.get_genre().equals(preferences[0])) {
            finalScore -= 100 * (priorities[0] / 100);
        } else if (song.get_genre().equals(preferences[1])) {
            finalScore -= 50 * priorities[0] / 100;
        } else if (song.get_genre().equals(preferences[2])) {
            finalScore -= 20 * priorities[0] / 100;
        }

        // Artist Score
        for (String artist : song.get_artist()) {
            if (artist.equals(preferences[3])) {
                finalScore -= 100 * priorities[1] / 100;
            } else if (artist.equals(preferences[4])) {
                finalScore -= 50 * priorities[1] / 100;
            } else if (artist.equals(preferences[5])) {
                finalScore -= 20 * priorities[1] / 100;
            }
        }
        
        // Popolarity
        int popularity_score = (int) ((100 - Math.abs(song.get_popularity() - Integer.parseInt(preferences[6]))) * priorities[2] / 100);
        finalScore -= popularity_score;

        // Duration
        int duration_score = (int) ((100 - Math.abs(song.get_duration() - Integer.parseInt(preferences[7]))) * priorities[3] / 100);
        finalScore -= duration_score;

        if (song.get_explicit() == Boolean.parseBoolean(preferences[8])) {
            finalScore -= 100 * priorities[4] / 100;
        }

        // Danceability
        int danceability_score = (int) ((100 - Math.abs(song.get_dance() - Integer.parseInt(preferences[9]))) * priorities[5] / 100);
        finalScore -= danceability_score;

        // Loudness
        int loudness_score = (int) ((100 - Math.abs(song.get_loudness() - Integer.parseInt(preferences[10]))) * priorities[6] / 100);
        finalScore -= loudness_score;

        // Acoustic
        int acoustic_score = (int) ((100 - Math.abs(song.get_acoustic() - Integer.parseInt(preferences[11]))) * priorities[7] / 100);
        finalScore -= acoustic_score;

        // Instrumental
        int instrumental_score = (int) ((100 - Math.abs(song.get_instrumental() - Integer.parseInt(preferences[12]))) * priorities[8] / 100);
        finalScore -= instrumental_score;

        // Valence
        int valence_score = (int) ((100 - Math.abs(song.get_valence() - Integer.parseInt(preferences[13]))) * priorities[9] / 100);
        finalScore -= valence_score;

        // Tempo
        int tempo_score = (int) ((100 - Math.abs(song.get_tempo() - Integer.parseInt(preferences[14]))) * priorities[10] / 100);
        finalScore -= tempo_score;

        // Energy
        int energy_score = (int) ((100 - Math.abs(song.get_energy() - Integer.parseInt(preferences[15]))) * priorities[11] / 100);
        finalScore -= energy_score;

        // Liveness
        int liveness_score = (int) ((100 - Math.abs(song.get_liveness() - Integer.parseInt(preferences[16]))) * priorities[12] / 100);
        finalScore -= liveness_score;

        return finalScore;
    }

    // Updates scores for all the songs in the heap baed on the updated 
    // preferences and priorities 
    public void updateAllScore() {
        for (Node node : this.heap.nodeHashMap.values()) {
            Song song = node.get_song();
            song.score = Integer.MAX_VALUE;
            int newScore = score(song, this.preferences, this.priorities);
            heap.updateScore(song.get_id(), newScore);
        }
    }

    // Gets preferences from terminal 
    public String[] getUserPreferences() {
        String[] str = new String[17];
        Scanner scanner = new Scanner(System.in); 
        System.out.println("What are your top 3 genres?");
        str[0] = scanner.nextLine();
        str[1] = scanner.nextLine();
        str[2] = scanner.nextLine();
        System.out.println("Who are your top 3 artists?");
         str[3] = scanner.nextLine();
         str[4] = scanner.nextLine();
         str[5] = scanner.nextLine();
        System.out.println("What's your preferred popularity level (1-100)?");
         str[6] = scanner.nextLine();
        System.out.println("What's your preferred song duration?");
         str[7] = scanner.nextLine();
        System.out.println("Are you ok with explicit songs?");
         str[8] = scanner.nextLine();
        System.out.println("ًWhat's your preferred danceability level (1-100)?");
         str[9] = scanner.nextLine();
        System.out.println("What's your preferred loudness level (1-100)?");
         str[10] = scanner.nextLine();
        System.out.println("Do you like accoustic songs (1-100)?");
         str[11] = scanner.nextLine();
        System.out.println("How instrumental do you want the song to be (1-100)?");
         str[12] = scanner.nextLine();
        System.out.println("What valence do you want (1-100)?");
         str[13] = scanner.nextLine();
        System.out.println("What's your preferred tempo (1-100)?");
         str[14]=scanner.nextLine();
        System.out.println("What's the preferred energy?");
         str[15]=scanner.nextLine();
        System.out.println("What's your preferred liveness?");
         str[16]= scanner.nextLine();


    return str;
    }

    // Gets prioroties from terminal 
    public int[] getUSerPriorities() {
        int[] arr = new int[13];
        int sum = 100;
        System.out.println("You've got a 100 points on how important each song attribute is in genarating your playlist. Enter 14 numbers in the same order of the questions above for how many points you want to allocate to each attribute. Press Enter in between numbers");
        Scanner scanner = new Scanner(System.in);
        for (int i =0; i<13; i++)
        {
             arr[i] = scanner.nextInt();
             sum -= arr[i];
             if (i<12)
             {
                         System.out.println("You've got " + sum + " points left");

             }
             else 
             {
                System.out.println("Thank you for your input! Generating playlist ....");
             }

        }
        return arr;

    }

    // Returns a playlist of songs based on song scores
    public String[] playlist(int n) {
        Set<String> arr = new HashSet<>();
        Set<Node> arr1 = new HashSet<>();
        // Extract the n songs with the smallest score
        while (arr.size() < n) {
            Node node = this.heap.extractMin();
            arr1.add(node);
            Song songs = node.get_song();
            String artist = Arrays.toString(songs.get_artist());
            String title = songs.get_name();
            arr.add(title + " by " + artist);
        }

        // Reinsurt the extracted songs back into the Heap
        for (Node node : arr1) {
            this.heap.insert(node.get_key(), node.get_song(), node.get_Id());
        }
        return arr.toArray(new String[arr.size()]);
    }

    public static void testPlaylist() {
        // Define test data
        String filePath = "MusicDataSet.csv";
        String[] testPreferences = {"pop", "rock", "jazz", "Sia", "Taylor Swift", "Artist3", "50", "200", "true", "50", "60", "70", "40", "30", "100", "80", "70"};
        int[] testPriorities = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        
        // Initilization
        Ingest ingest = new Ingest(filePath, testPreferences, testPriorities);
        
        // Generate Playlist of 7 songs
        String[] playlist = ingest.playlist(6);
        
        // Print the playlist
        System.out.println("Generated Playlist:");
        for (String song : playlist) {
            System.out.println(song);
        }
        
        // Ensure no duplicate songs
        Set<String> songSet = new HashSet<>(Arrays.asList(playlist));
        if (songSet.size() != playlist.length) {
            System.out.println("Error: Playlist contains duplicate songs.");
        } else {
            System.out.println("Playlist contains unique songs.");
        }
    }

    public static void main(String[] args) {
        // Test the playlist function
        testPlaylist();
    }
}
