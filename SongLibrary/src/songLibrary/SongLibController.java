package songLibrary;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongLibController {
	
	@FXML ListView<Song> lv;
	@FXML TextField nameInput;
	@FXML TextField artistInput;
	@FXML TextField albumInput;
	@FXML TextField yearInput;
	
	private ObservableList<Song> playList;
	
	public void start(Stage mainStage){
		
		playList = FXCollections.observableArrayList();

		lv.setItems(playList);
		lv.getSelectionModel().select(0);
	}
	public void addAction(ActionEvent e){
		String name = nameInput.getText();
		String artist = artistInput.getText();
		String album = albumInput.getText();
		String year = yearInput.getText();
		
		if (name=="" && artist=="" && album=="" && year==""){
			Alert emptyInput = new Alert(AlertType.INFORMATION);
			emptyInput.setTitle("Invalid Input");
			emptyInput.setHeaderText("You did not enter any text. No song were added.");
			emptyInput.setContentText("Click OK to continue");

			emptyInput.showAndWait();
		} else {
			addSong(name, artist, album, year);
		}
	}
	public void editAction(ActionEvent e){
		
	}
	public void deleteAction(ActionEvent e){
		
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
