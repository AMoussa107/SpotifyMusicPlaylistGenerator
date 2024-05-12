package music;
public class application{
public int score(SongHashMap songs, String [] preferences, String [] priorities){
	
 	
}
public String[] getUserPreferences()
{
	String[] str = new String[18];
	Scanner scanner = new Scanner(); 
	System.out.println("What are your top 3 genres?");
	String str[0] = scanner.nextLine();
	String str[1] = scanner.nextLine();
	String str[2] = scanner.nextLine();
	System.out.println("Who are your top 3 artists?");
	String str[3] = scanner.nextLine();
	String str[4] = scanner.nextLine();
	String str[5] = scanner.nextLine();
	System.out.println("What's your favorite album?");
	String str [6] = scanner.nextLine();
	System.out.println("What's your preferred popularity level (1-100)?");
	String str[7] = scanner.nextLine();
	System.out.println("What's your preferred song duration?");
	String str[8] = scanner.nextLine();
	System.out.println("Are you ok with explicit songs?");
	String str[9] = scanner.nextLine();
	System.out.println("ًWhat's your preferred danceability level (1-100)?");
	String str[10] = scanner.nextLine();
	System.out.println("What's your preferred loudness level (1-100)?");
	String str[11] = scanner.nextLine();
	System.out.println("Do you like accoustic songs (1-100)?");
	String str[12] = scanner.nextLine();
	System.out.println("How instrumental do you want the song to be (1-100)?");
	String str[13] = scanner.nextLine();
	System.out.println("What valence do you want (1-100)?");
	String str[14] = scanner.nextLine();
	System.out.println("What's your preferred tempo (1-100)?");
	String str[15]=scanner.nextLine();
	System.out.println("What's the preferred energy?");
	String str[16]=scanner.nextLine();
	System.out.println("What's your preferred liveness?");
	String str[17]= scanner.nextLine();


return str;

}
public int [] getUSerPriorities()
{
	int [] arr = new arr[14];
	int sum = 100;
	System.out.println("You've got a 100 points on how important each song attribute is in genarating your playlist. Enter 14 numbers in the same order of the questions above for how many points you want to allocate to each attribute. Press Enter in between numbers");
	Scanner scanner = new Scanner();
	for (int i =0; i<15; i++)
	{
		 arr[i] = scanner.nextInt();
		 sum -= arr[i];
		 if (i<14)
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
		
}
}
