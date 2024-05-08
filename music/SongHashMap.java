package music;
import java.util.*;
public class SongHashMap {
	protected Map<String, Map<String, List<String>>> hashMap;
	public SongHashMap() {
        this.hashMap = new HashMap<>();
    }
    // Method to categorize songs based on attributes
   protected static void categorizeSong(Map<String, Map<String, List<String>>> hashMap, Song song) {
    // Categorize by genre
    categorizeAttribute(hashMap, "Genre", song.get_genre(), song.get_id());

    // Categorize by artist
    for (String artist : song.get_artist()) {
        categorizeAttribute(hashMap, "Artist", artist, song.get_id());
    }

    // Categorize by album
    categorizeAttribute(hashMap, "Album", song.get_album(), song.get_id());

    // Categorize by popularity
    categorizeAttribute(hashMap, "Popularity", String.valueOf(song.get_popularity()), song.get_id());

    // Categorize by duration
    categorizeAttribute(hashMap, "Duration", String.valueOf(song.get_duration()), song.get_id());

    // Categorize by energy
    categorizeAttribute(hashMap, "Energy", String.valueOf(song.get_energy()), song.get_id());

    // Categorize by loudness
    categorizeAttribute(hashMap, "Loudness", String.valueOf(song.get_loudness()), song.get_id());

    // Categorize by acoustic
    categorizeAttribute(hashMap, "Acoustic", String.valueOf(song.get_acoustic()), song.get_id());

    // Categorize by instrumental
    categorizeAttribute(hashMap, "Instrumental", String.valueOf(song.get_instrumental()), song.get_id());

    // Categorize by liveness
    categorizeAttribute(hashMap, "Liveness", String.valueOf(song.get_liveness()), song.get_id());

    // Categorize by valence
    categorizeAttribute(hashMap, "Valence", String.valueOf(song.get_valence()), song.get_id());

    // Categorize by speechiness
    categorizeAttribute(hashMap, "Speechiness", String.valueOf(song.get_speechiness()), song.get_id());

    // Categorize by tempo
    categorizeAttribute(hashMap, "Tempo", String.valueOf(song.get_tempo()), song.get_id());

    // Categorize by explicit
    categorizeAttribute(hashMap, "Explicit", String.valueOf(song.get_explicit()), song.get_id());
        }
        private static void categorizeAttribute(Map<String, Map<String, List<String>>> hashMap, String category, String attribute, String songId) {
                hashMap.computeIfAbsent(category, k -> new HashMap<>())
                        .computeIfAbsent(attribute, k -> new ArrayList<>())
                        .add(songId);
        }

        public static void main(String[] args) {
                
                
        }



}
    

