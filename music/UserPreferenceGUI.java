package music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class UserPreferenceGUI extends JFrame {
    // Initializing three dropdown menus to rank the top 3 genres
    private JComboBox<String>[] genreComboBoxes = new JComboBox[3];
    // One priority slider for the genres' importance
    private JSlider genrePrioritySlider;
    // Initializing three dropdown menus to rank the top 3 artists
    private JTextField[] artistFields = new JTextField[3];
    // One priority slider for the artists' importance
    private JSlider artistPrioritySlider;
    // An array of sliders for attributes that have numerical values, and their priority sliders
    private JSlider[] attributeSliders = new JSlider[11];
    private JSlider[] prioritySliders = new JSlider[11];
    // Text box for the number of songs
    private JTextField numberSongs;
    // CheckBox for Including Explicit songs
    private JCheckBox explicitCheckBox;
    // Submit Preferences and generate playlist button
    private JButton submitButton;

    // preferences input values from the user
    private static String[] preferences;
    // priorities input values from the user
    private static int[] priorities;
    // Initializing an ingest instance to construct the Fibonacci heap for the first time
    private static Ingest ingestInstance;
    // Checking if it's the first time to submit preferences
    private boolean firstTime = true;
    // File path for our music data set
    private String filePath = "MusicDataSet.csv";

    // Constructor
    public UserPreferenceGUI() {
        // Building the frame
        setTitle("Music Playlist Preferences");
        setSize(800, 900);
        // Exit the program when you click close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 4, 10, 10));

        // Attributes labels
        String[] attributes = new String[]{"popularity", "duration", "danceability", "energy", "loudness",
                "speechiness", "acoustic", "instrumental", "liveness", "valence", "tempo"};
                // Genre selection labels, and adding genres to the dropdown menu and adding the menus to the interface
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

        //Adding artist labels and the artsits textboxes to the interface
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

        // Adding the labels and the components of attributes preferences selection to the interface
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

        // Adding the explicity checkbox to the interface
        add(new JLabel("Are you okay with explicit songs?"));
        explicitCheckBox = new JCheckBox();
        add(explicitCheckBox);

        // Number of songs text box added to the interface
        add(new JLabel("Number of songs:"));
        numberSongs = new JTextField("0");
        add(numberSongs);

        // Pressable Button to generate playlist
        submitButton = new JButton("Generate Playlist");
        submitButton.addActionListener(this::submitPreferences);
        add(submitButton);

        setVisible(true);
    }

    // Mouse listener for the sliders
    class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Returning the value from the slider
            int value = genrePrioritySlider.getValue();
        }
    }

  // Generating the playlist after pressing the button
    private void submitPreferences(ActionEvent e) {
        // Parsing all values of preferences and priorities
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

        // Initialize ingest instance if it's first time
        if (firstTime) {
            ingestInstance = new Ingest(filePath, preferences, priorities);
            firstTime = false;
        }
        // update the scores of the current priority queue 
         else {
            ingestInstance.preferences = preferences;
            ingestInstance.priorities = priorities;
            ingestInstance.updateAllScore();
        }

        // Generate playlist
        String[] arr = ingestInstance.playlist(Integer.parseInt(numberSongs.getText()));

        // Display the playlist in a new window
        displayPlaylist(arr);
    }

    // Method to display playlist
    private void displayPlaylist(String[] playlist) {
        JFrame playlistFrame = new JFrame("Generated Playlist");
        playlistFrame.setSize(400, 300);
        playlistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JList<String> playlistList = new JList<>(playlist);
        JScrollPane scrollPane = new JScrollPane(playlistList);

        playlistFrame.add(scrollPane);
        playlistFrame.setVisible(true);
    }

    // list of genres in the data set
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
