package music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserPreferenceGUI extends JFrame {
    private JComboBox<String>[] genreComboBoxes = new JComboBox[3];
    private JTextField[] artistFields = new JTextField[3];
    private JSlider popularitySlider, durationSlider, danceabilitySlider, energySlider, loudnessSlider,
            speechinessSlider, acousticSlider, instrumentalSlider, livenessSlider, valenceSlider, tempoSlider;
    private JCheckBox explicitCheckBox;

    public UserPreferenceGUI() {
        setTitle("Music Playlist Preferences");
        setSize(650, 700); // Adjusted the size for better fit of all components
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        // Genre selection with rankings
        for (int i = 0; i < 3; i++) {
            add(new JLabel("Choose your " + (i == 0 ? "top" : (i == 1 ? "second" : "third")) + " genre:"));
            genreComboBoxes[i] = new JComboBox<>(getGenres());
            add(genreComboBoxes[i]);
        }

        // Artist input with rankings
        for (int i = 0; i < 3; i++) {
            add(new JLabel("Enter your " + (i == 0 ? "favorite" : (i == 1 ? "second favorite" : "third favorite")) + " artist:"));
            artistFields[i] = new JTextField();
            add(artistFields[i]);
        }

        // Sliders for various attributes
        addSlider("Preferred popularity (1-100):", popularitySlider = new JSlider(1, 100, 50));
        addSlider("Preferred duration (1-100 minutes):", durationSlider = new JSlider(1, 100, 50));
        addSlider("Preferred danceability (1-100):", danceabilitySlider = new JSlider(1, 100, 50));
        addSlider("Preferred energy (1-100):", energySlider = new JSlider(1, 100, 50));
        addSlider("Preferred loudness (1-100):", loudnessSlider = new JSlider(1, 100, 50));
        addSlider("Preferred speechiness (1-100):", speechinessSlider = new JSlider(1, 100, 50));
        addSlider("Preferred acoustic (1-100):", acousticSlider = new JSlider(1, 100, 50));
        addSlider("Preferred instrumental (1-100):", instrumentalSlider = new JSlider(1, 100, 50));
        addSlider("Preferred liveness (1-100):", livenessSlider = new JSlider(1, 100, 50));
        addSlider("Preferred valence (1-100):", valenceSlider = new JSlider(1, 100, 50));
        addSlider("Preferred tempo (1-100):", tempoSlider = new JSlider(1, 100, 50));

        // Explicit content checkbox
        add(new JLabel("Are you okay with explicit songs?"));
        explicitCheckBox = new JCheckBox();
        add(explicitCheckBox);

        // Submit button to handle preferences
        JButton submitButton = new JButton("Generate Playlist");
        submitButton.addActionListener(this::submitPreferences);
        add(submitButton);

        setVisible(true);
    }

    private void submitPreferences(ActionEvent e) {
        System.out.println("Genres:");
        for (JComboBox<String> comboBox : genreComboBoxes) {
            System.out.println(comboBox.getSelectedItem());
        }
        System.out.println("Artists:");
        for (JTextField textField : artistFields) {
            System.out.println(textField.getText());
        }
        System.out.println("Popularity: " + popularitySlider.getValue());
        System.out.println("Valence: " + valenceSlider.getValue());
        System.out.println("Explicit: " + explicitCheckBox.isSelected());
    }

    private void addSlider(String label, JSlider slider) {
        add(new JLabel(label));
        add(slider);
    }

    private String[] getGenres() {
        return new String[]{"select genre", "chill", "afrobeat", "sertanejo", "goth", "house", "pagode", "psych-rock",
                "trance", "salsa", "progressive-house", "rock", "pop", "jazz"};
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserPreferenceGUI::new);
    }
}
