package songLibrary;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SongLibController {
	
	@FXML
	ListView<String> lv;
	
	private ObservableList<Song> playList;
	private ObservableList<String> playListStrings;
	
	public void start(Stage mainStage){
		playListStrings = FXCollections.observableArrayList("elephants");

		lv.setItems(playListStrings);
		lv.getSelectionModel().select(0);
	}
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
