package music;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class ingest{
    private SongHashMap songHashMap;

    public void ingestCSV(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            SongHashMap songHashMap = new SongHashMap();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
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
                Song song = new Song(id, artists, album, name, popularity, duration, explicit, dance, energy, loudness, speechiness, accoustic, instrumental, liveness, valence, tempo, genre);
                songHashMap.categorizeSong(songHashMap.hashMap,song);
        } 
            scanner.close();
        }   
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] args) {

        String filePath = "MusicDataSet.csv";
        String filePathToy = "Sampeldata.csv";
        ingest ingestInstance = new ingest();
        ingestInstance.ingestCSV(filePathToy);
        for (String key : ingestInstance.songHashMap.hashMap.keySet()) {
            System.out.println(key);
        }
        
        
       

    }
}

