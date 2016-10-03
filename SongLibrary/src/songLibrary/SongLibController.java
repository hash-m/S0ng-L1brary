package songLibrary;

import java.util.Collections;

import javafx.collections.ObservableList;

public class SongLibController {
	
	private ObservableList<Song> playList;
	
	public void addSong(String name,String artist, String album, String year){
		
		Song song = new Song(name, artist, album, year);
		
		if(playList.isEmpty()){
			playList.add(song);
		}
		else{
			//Collections.sort(playList.name);
		}
		
		
		
	}
	
	

}
