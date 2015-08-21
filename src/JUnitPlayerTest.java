/**
 * Simple class for testing out JUnit, used in class project with a simulated music player.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class JUnitPlayerTest {
	
	Player testPlayer = new Player();

	@Test
	public void testConstructor() {
		assertEquals(0, testPlayer.getNumberOfSongs(), 0);
	}
	
	@Test
	public void testAddSong() {
		testPlayer.addSong("");
		//First test case is one song.
		testPlayer.addSong("Test Song");
		assertEquals(1, testPlayer.getNumberOfSongs(), 1);
		//j = number of songs to test 100 in this case, accounts for initial song added
		for (int j=100; j>0; j--) {
			testPlayer.addSong("Test Song " + j);
		}
		assertEquals(100, testPlayer.getNumberOfSongs(), 100);
		//commented out as it will cause a failure and extreme system hang
		//k = testing a very large number of songs, causes failure around 1 billion
//		for(int k=1000000000; k>0; k--) {
//			testPlayer.addSong("Test Song " + k);
//		}
//		assertEquals(1000000100, testPlayer.getNumberOfSongs(), 1000000100);
	}
	
	@Test
	public void testPlay() {
		//Play test of 0 songs, should results in an end
		testPlayer = new Player();
		assertEquals("end", testPlayer.play(), "end");
		//Play test of 5 songs, first they are added, play is then called 6 times.  Result should be
		//a return of "end".
		for(int i=5;i>0;i--) {
			testPlayer.addSong("Test Song " + i);
		}
		for(int i=5;i>0;i--) {
			testPlayer.play();
		}
		assertEquals("end", testPlayer.play(), "end");
	}
	
	@Test
	public void testShuffle() {
		//Test of shuffle method with 1000 songs. Random Number Generation may result in 
		//the arrays being the same at times.  Expect a failure on this test as it shows
		//that the shuffle method is working.
		testPlayer = new Player();
		for(int i = 1000; i>0; i--) {
			testPlayer.addSong("Song " + i);
		}
		String[] shuffleOne = new String[testPlayer.getNumberOfSongs()+1];
		String[] shuffleTwo = new String[testPlayer.getNumberOfSongs()+1];
		testPlayer.shuffle();
		for(int j=0; j<shuffleOne.length; j++) {
			shuffleOne[j] = testPlayer.play();
		}
		testPlayer.shuffle();
		for(int k=0; k<shuffleTwo.length; k++) {
			shuffleTwo[k] = testPlayer.play();
		}
		assertArrayEquals("Failure is expected", shuffleOne, shuffleTwo);		
	}
}
