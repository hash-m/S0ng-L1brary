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
	
	public void delete(Song a){
		
		if(!playList.isEmpty()){
			int index= list.getSelectionModel().getSelectedIndex();
			playList.remove(index);	
		}

	}
	public void edit(Song a, String name, String artist, String album, String year){
		Song b = new Song("","","",""); 
		if(a.getName().isEmpty()){
			b.setName(a.getName());
		}
		else{
			b.setName(name);
		}
		if(a.getArtist().isEmpty()){
			b.setArtist(a.getArtist());
		}
		else{
			b.setArtist(artist);
		}
		if(a.getAlbum().isEmpty()){
			b.setAlbum(a.getAlbum());
		}
		else{
			b.setAlbum(name);
		}
		if(a.getYear().isEmpty()){
			b.setYear(a.getYear());
		}
		else{
			b.setYear(year);
		}
		
		delete (a);
		addSong(b.getName(),b.getArtist(),b.getAlbum(),b.getYear());
	}		
}
	
