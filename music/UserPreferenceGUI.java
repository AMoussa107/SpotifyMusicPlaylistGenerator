package music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class UserPreferenceGUI extends JFrame {
    private JComboBox<String>[] genreComboBoxes = new JComboBox[3];
    private JTextField[] artistFields = new JTextField[3];
    private JTextField numberSongs = new JTextField();
    private JSlider[] attributeSliders, prioritySliders;
    private JSlider genrePrioritySlider, artistPrioritySlider;
    private JLabel remainingPriorityLabel;
    private JCheckBox explicitCheckBox;
    private int totalPriority = 100;
    private static String[] preferences;
    private static int[] priorities;
    private String filePath = "MusicDataSet.csv";
    private static Ingest ingestInstance;
    private boolean firstTime = true;

    public UserPreferenceGUI() {
        setTitle("Music Playlist Preferences");
        setSize(800, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 4, 10, 10));
        String[] attributes = new String[]{"popularity", "duration", "danceability", "energy", "loudness",
                "speechiness", "acoustic", "instrumental", "liveness", "valence", "tempo"};

       
        for (int i = 0; i < 3; i++) {
            add(new JLabel("Genre " + (i + 1) + ":"));
            genreComboBoxes[i] = new JComboBox<>(getGenres());
            add(genreComboBoxes[i]);
            if (i == 0) {
                add(new JLabel("Genre Priority:"));
                genrePrioritySlider = new JSlider(0, 100, 0);
                add(genrePrioritySlider);
                genrePrioritySlider.addMouseListener(new MouseListener());
            } else {
                add(new JLabel(""));
                add(new JLabel(""));
            }
        }

        for (int i = 0; i < 3; i++) {
            add(new JLabel("Artist " + (i + 1) + ":"));
            artistFields[i] = new JTextField();
            add(artistFields[i]);
            if (i == 0) {
                add(new JLabel("Artist Priority:"));
                artistPrioritySlider = new JSlider(0, 100, 0);
                add(artistPrioritySlider);
                artistPrioritySlider.addMouseListener(new MouseListener());
            } else {
                add(new JLabel(""));
                add(new JLabel(""));
            }
        }

        attributeSliders = new JSlider[11];
        prioritySliders = new JSlider[11];
        for (int i = 0; i < attributeSliders.length; i++) {
             add(new JLabel(attributes[i]));
       attributeSliders[i] = new JSlider(0, 100, 0);
                add(attributeSliders[i]);
                attributeSliders[i].addMouseListener(new MouseListener());
        add(new JLabel(attributes[i] + "Priority:"));
        prioritySliders[i] = new JSlider(0, 100, 0);
                add(prioritySliders[i]);
                prioritySliders[i].addMouseListener(new MouseListener());
        }

        add(new JLabel("Are you okay with explicit songs?"));
        explicitCheckBox = new JCheckBox();
        add(explicitCheckBox);
       

        add(new JLabel("Number of songs:"));
        numberSongs.setText("0");
        add(numberSongs);
        JButton submitButton = new JButton("Generate Playlist");
        submitButton.addActionListener(this::submitPreferences);
        add(submitButton);
     

        setVisible(true);
    }
class MouseListener extends MouseAdapter {
   @Override
    public void mouseClicked(MouseEvent e) {
        int value = genrePrioritySlider.getValue();
    }
}
    private void submitPreferences(ActionEvent e) {
        preferences = new String[genreComboBoxes.length + artistFields.length + attributeSliders.length];
        priorities = new int[2 + prioritySliders.length]; // Two for genre and artist priorities and the rest for attributes

        for (int i = 0; i < genreComboBoxes.length; i++) {
            preferences[i] = (String) genreComboBoxes[i].getSelectedItem();
        }
        priorities[0] = genrePrioritySlider.getValue();

        for (int i = 0; i < artistFields.length; i++) {
            preferences[i + genreComboBoxes.length] = artistFields[i].getText();
        }
        priorities[1] = artistPrioritySlider.getValue();

        for (int i = 0; i < attributeSliders.length; i++) {
            preferences[i + genreComboBoxes.length + artistFields.length] = String.valueOf(attributeSliders[i].getValue());
            priorities[i + 2] = prioritySliders[i].getValue();
        }

        if (firstTime) {
            ingestInstance = new Ingest(filePath, preferences, priorities);
            firstTime = false;
        } else {
            ingestInstance.preferences = preferences;
            ingestInstance.priorities = priorities;
            ingestInstance.updateAllScore();
        }

        String[] arr = ingestInstance.playlist(Integer.parseInt(numberSongs.getText()));

        // Display the playlist in a new window
        displayPlaylist(arr);
    }

    private void displayPlaylist(String[] playlist) {
        JFrame playlistFrame = new JFrame("Generated Playlist");
        playlistFrame.setSize(400, 300);
        playlistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JList<String> playlistList = new JList<>(playlist);
        JScrollPane scrollPane = new JScrollPane(playlistList);

        playlistFrame.add(scrollPane);
        playlistFrame.setVisible(true);
    }

    private String[] getGenres() {
        return new String[]{"select genre", "afrobeat", "acoustic", "alt-rock", "alternative", "ambient", "anime", "black-metal", "bluegrass", "blues", "brazil", "breakfast", "british", "cantopop", "chicago-house", "children", "chill", "classical", "club", "comedy", "country", "dance", "dance-hall", "death-metal", "deep-house", "detroit-techno", "disney", "disco", "drum-and-bass", "dub", "dubstep", "edm", "electro", "electronic", "emo", "folk", "forro", "french", "funk", "garage", "german", "gospel", "goth", "grindcore", "groove", "grunge", "guitar", "happy", "hard-rock", "hardcore", "hardstyle", "heavy-metal", "hip-hop", "honky-tonk", "house", "idm", "indian", "indie", "indie-pop", "industrial", "iranian", "j-dance", "j-idol", "j-pop", "j-rock", "jazz", "k-pop", "kids", "latin", "latino", "malay", "mandopop", "metal", "metalcore", "minimal-techno", "mpb", "new-age", "opera", "pagode", "party", 
"piano", "pop-film", "pop", "power-pop", "progressive-house", "psych-rock", "punk-rock", "punk", "r-n-b",
"reggae", "reggaeton", "rock-n-roll", "rock", "rockabilly", "romance", "sad", "salsa", "samba", "sertanejo",
"show-tunes", "singer-songwriter", "ska", "sleep", "songwriter", "soul", "spanish", "study", "swedish", 
"synth-pop", "tango", "techno", "trance", "trip-hop", "turkish", "world-music"};
    }

    public static void main(String[] args) {
        UserPreferenceGUI playlistGenerator = new UserPreferenceGUI();
    }
}
