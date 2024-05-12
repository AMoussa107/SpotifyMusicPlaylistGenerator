package music;
import java.util.*;
public class SongHashMap {
	protected Map<String, Map<String, List<Song>>> hashMap;
	public SongHashMap() {
        this.hashMap = new HashMap<>();
    }
    // Method to categorize songs based on attributes
   protected static void categorizeSong(Map<String, Map<String, List<Song>>> hashMap, Song song) {
    // Categorize by genre
    categorizeAttribute(hashMap, "Genre", song.get_genre(), song);

    // Categorize by artist
    for (String artist : song.get_artist()) {
        categorizeAttribute(hashMap, "Artist", artist, song);
    }

    // Categorize by album
    categorizeAttribute(hashMap, "Album", song.get_album(), song);

    // Categorize by popularity
    categorizeAttribute(hashMap, "Popularity", String.valueOf(song.get_popularity()), song);

    // Categorize by duration
    categorizeAttribute(hashMap, "Duration", String.valueOf(song.get_duration()), song);

    // Categorize by energy
    categorizeAttribute(hashMap, "Energy", String.valueOf(song.get_energy()), song);

    // Categorize by loudness
    categorizeAttribute(hashMap, "Loudness", String.valueOf(song.get_loudness()), song);

    // Categorize by acoustic
    categorizeAttribute(hashMap, "Acoustic", String.valueOf(song.get_acoustic()), song);

    // Categorize by instrumental
    categorizeAttribute(hashMap, "Instrumental", String.valueOf(song.get_instrumental()), song);

    // Categorize by liveness
    categorizeAttribute(hashMap, "Liveness", String.valueOf(song.get_liveness()), song);

    // Categorize by valence
    categorizeAttribute(hashMap, "Valence", String.valueOf(song.get_valence()), song);

    // Categorize by speechiness
    categorizeAttribute(hashMap, "Speechiness", String.valueOf(song.get_speechiness()), song);

    // Categorize by tempo
    categorizeAttribute(hashMap, "Tempo", String.valueOf(song.get_tempo()), song);

    // Categorize by explicit
    categorizeAttribute(hashMap, "Explicit", String.valueOf(song.get_explicit()), song);
        }
        private static void categorizeAttribute(Map<String, Map<String, List<Song>>> hashMap, String category, String attribute, Song song) {
                hashMap.computeIfAbsent(category, k -> new HashMap<>())
                        .computeIfAbsent(attribute, k -> new ArrayList<>())
                        .add(song);
        }

        public static void main(String[] args) {
                
                
        }



}
    

