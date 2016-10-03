package songLibrary;

import java.util.Collections;

import javafx.collections.ObservableList;

public class SongLibController {
	
	private ObservableList<Song> playList;
	
	public void addSong(String name,String artist, String album, String year){
		
		Song song = new Song(name, artist, album, year);
		Song temp;
		if(playList.isEmpty()){
			playList.add(song);
		}
		else{
			for (int i=0;i<playList.size();i++){
			 for (int j=1;j<(playList.size()-i);j++){
	                if (playList.get(j-1).compareTo(playList.get(j) )>=0)
	                {
	                    temp=playList.get(j-1);
	                    playList.remove(j-1);
	                    playList.add(j, temp);
	                }
	            }
			}
		}
		
	}
	
}
