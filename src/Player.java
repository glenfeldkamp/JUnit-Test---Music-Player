/**
 * Simple fake music player to use in a JUnit testing program.
 */
import java.util.*;


public class Player 
{
	private String[] songs;
	private int[] order;
	private int numSongs;
	private int numSongsInShuffle;
	
	public Player()
	{
		songs = null;
		order = null;
		numSongs = 0;
		numSongsInShuffle = 0;
	}
	
	public void addSong(String name)
	{
		if (songs==null)
		{
			//redesigned add method for null size
			songs = new String[1];
			//
		}
		// Made else if statement for this because 2*0 would result in a 0 length array
		else if (numSongs==songs.length) {
		//
			String [] temp;
			temp = new String[2*numSongs];
			for (int i=0;i<songs.length;i++)
				temp[i] = songs[i];
			songs = temp;

		}
		songs[numSongs] = name;
		numSongs++;
	}
	
	public int getNumberOfSongs()
	{
		return numSongs;
	}
	
	public void shuffle()
	{
		// Entirely rewrote the shuffle method as initially it didn't seem to have a purpose.
		// Shuffle is now completed by the shuffle method rather than in the play method.
		int key = 0;
		if (songs==null) {
			order = null;
			numSongsInShuffle = 0;
		}
		if (songs!=null && songs.length>=1) {
			int a = 0;
			int b = 0;
			order = new int[numSongs];
			numSongsInShuffle = order.length;
			for(int i = 0;i<numSongsInShuffle;i++) {
				order[i] = i;
			}
			for(int j=0;j<numSongsInShuffle;j++) {
				key = (int)(Math.random()*(numSongsInShuffle-1));
				a = order[j];
				b = order[key];
				order[j] = b;
				order[key] = a;				
			}
			//
		}
	}
	
	public String play()
	{
		//Removed unnecessary int and added brackets to avoid errors.
		if (numSongsInShuffle==0) {
			return "end";
		}
		//
		// Rewrote this method as I moved the shuffling to the shuffle method.
		String song = songs[order[numSongsInShuffle-1]];
		numSongsInShuffle--;
		return song;		
		//
	}
	
	//Main method to see the methods in action.
	public static void main(String []args) {
		Player test = new Player();
		//j = amount of songs to test with; problems occur after 1 billion.
		for(int j=100; j>0; j--) {
			test.addSong("test song " + j);
		}
		System.out.println(test.getNumberOfSongs());
		test.shuffle();
		//Plays all songs in shuffled play list.
		for(int i=test.numSongsInShuffle-1;i>=-1;i--) {
			System.out.println(test.play());
		}
	}
}
