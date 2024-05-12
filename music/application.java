package music;
import java.util.*;
public class application{
	private ingest ingestInstance;
	public  application(String filePath) {
		ingestInstance = new ingest(filePath);
	}


	public void score(Song song, String [] preferences, int[] priorities){
		//genre
		if (song.get_genre() == preferences[0]) {
			song.score -= 10000 * (priorities[0]/100);
		}

		else if (song.get_genre() == preferences[1]) {
			song.score -= 5000 * priorities[0]/100;
		}
		else if (song.get_genre() == preferences[2]) {
			song.score -= 2000 * priorities[0]/100;
		}
		//artist
		for (String artist : song.get_artist()) {
			if (artist == preferences[3]) {
				song.score -= 10000 * priorities[1]/100;
			}
			else if (artist == preferences[4]) {
				song.score -= 5000 * priorities[1]/100;
			}
			else if (artist == preferences[5]) {
				song.score -= 2000 * priorities[1]/100;
			}

		}
		//album
		int popularity_score = (int) ((10000-100*(Math.abs(song.get_popularity() - Integer.parseInt(preferences[6]))))*priorities[2]/100);
		song.score -= popularity_score; 
		//duration
		int duration_score = (int) ((10000-100*(Math.abs(song.get_duration() - Integer.parseInt(preferences[7]))))*priorities[3]/100);
		song.score -= duration_score; 
		//explicity
		if (song.get_explicit() == Boolean.parseBoolean(preferences[8])) {
			song.score -= 10000 * priorities[4]/100; 
		}
		//danceability 
		int danceability_score = (int) ((10000-100*(Math.abs(song.get_dance() - Integer.parseInt(preferences[9]))))*priorities[5]/100);
		song.score -= danceability_score; 
		//loudness
		int loudness_score = (int) ((10000-100*(Math.abs(song.get_loudness() - Integer.parseInt(preferences[10]))))*priorities[6]/100);
		song.score -= loudness_score;
		//acoustic
		int acoustic_score = (int) ((10000-100*(Math.abs(song.get_acoustic() - Integer.parseInt(preferences[11]))))*priorities[7]/100);
		song.score -= acoustic_score;  
		//instrumental
		int instrumental_score =  (int) ((10000-100*(Math.abs(song.get_instrumental() - Integer.parseInt(preferences[12]))))*priorities[8]/100);
		song.score -= instrumental_score;  
		//valence
		int valence_score = (int) ((10000-100*(Math.abs(song.get_valence() - Integer.parseInt(preferences[13]))))*priorities[9]/100);
		song.score -= valence_score;
		//tempo
		int tempo_score = (int) ((10000-100*(Math.abs(song.get_tempo() - Integer.parseInt(preferences[14]))))*priorities[10]/100);
		song.score -= tempo_score;
		//energy
		int energy_score = (int) ((10000-100*(Math.abs(song.get_energy() - Integer.parseInt(preferences[15]))))*priorities[11]/100);
		song.score -= energy_score;
		//liveness
		int liveness_score = (int) ((10000-100*(Math.abs(song.get_liveness() - Integer.parseInt(preferences[16]))))*priorities[12]/100);
		song.score -= energy_score;
	}

public String[] getUserPreferences()
{
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
public int[] getUSerPriorities()
{
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
public static void main(String[] args) {
	application application = new application("MusicDataSet.csv");

	
}
}
