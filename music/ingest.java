package music;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Ingest {
    private SongHashMap songHashMap;

    public Ingest(String filePath) {
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
                    String id = attributes[1];
                    String[] artists = attributes[2].split(";");
                    String album = attributes[3];
                    String name = attributes[4];
                    Integer popularity = Integer.parseInt(attributes[5]);
                    Long duration = Long.parseLong(attributes[6]);
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

                    Song song = new Song(id, artists, album, name,  popularity, duration, explicit, dance, energy, loudness, speechiness, accoustic, instrumental, liveness, valence, tempo, genre);
                    songHashMap.categorizeSong(songHashMap.hashMap, song);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing number from file: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "toyMusicDataSet.csv";
        Ingest ingestInstance = new Ingest(filePath);
        if (ingestInstance.songHashMap != null && ingestInstance.songHashMap.hashMap != null) {
            System.out.println(ingestInstance.songHashMap.hashMap.keySet());
            System.out.println(ingestInstance.songHashMap.hashMap.get("Genre").get("romance"));
        } else {
            System.out.println("Failed to load songs or initialize data structure.");
        }

    }
}