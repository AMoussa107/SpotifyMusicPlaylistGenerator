package music;

public class Song{
	private String id;
	private String genre;
	private String name;
	private String[] artist;
	private String album;
	private Integer popularity;
	private Long duration;
	private boolean explicit;
	private double dance;
	private double energy;
	private double loudness;
	private double accoustic;
	private double instrumental;
	private double liveness;
	private double valence; 
	private double speechiness;
	private double tempo;

	public Song(String id, String[] artist, String album, String name,   Integer popularity, Long duration, boolean explicit, double dance, double energy, double loudness, double speechiness, double accoustic, double instrumental, double liveness, double valence,  double tempo, String genre) {
		this.id = id;
		this.genre = genre;
		this.name = name;
		this.artist = artist;
		this.energy = energy;
		this.album = album;
		this.popularity = popularity;
		this.duration = duration;
		this.loudness =loudness;
		this.accoustic = accoustic;
		this.instrumental = instrumental;
		this.liveness = liveness;
		this.valence = valence;
		this.speechiness = speechiness;
		this.tempo = tempo;
}
	public String get_name() {
		return name;
	}
	public String get_id() {
		return id;
	}
	public String get_genre() {
		return genre;
	}
	public String[] get_artist() {
		return artist;
	}
	public String get_album() {
		return album;
	}
	public Integer get_popularity() {
		return popularity;
	}
	public Long get_duration() {
		return duration;
	}
	public double get_energy()
	{
		return energy;
	}
	public double get_dance() {
		return dance;
	}
	public double get_loudness() {
		return loudness;
	}
	public double get_acoustic() {
		return accoustic;
	}
	public double get_instrumental() {
		return instrumental;
	}
	public double get_liveness() {
		return liveness;
	}
	public double get_valence() {
		return valence;
	}
	public double get_speechiness() {
		return speechiness;
	}
	public double get_tempo() {
		return tempo;
	}
	public boolean get_explicit()
	{
		return explicit;
	}

	public static void main(String[] args) {
		
	}

}