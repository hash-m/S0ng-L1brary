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
	
	@FXML ListView<String> lv;
	@FXML TextField nameInput;
	@FXML TextField artistInput;
	@FXML TextField albumInput;
	@FXML TextField yearInput;
	
	private ObservableList<Song> playList= 
			FXCollections.observableArrayList();
	private ObservableList<String> playListStrings=
			FXCollections.observableArrayList();
	
	public void start(Stage mainStage){
		refreshLVStrings(playList);
		lv.setItems(playListStrings);
		lv.getSelectionModel().select(0);
	}
	public void addAction(ActionEvent e){
		String name = nameInput.getText();
		String artist = artistInput.getText();
		String album = albumInput.getText();
		String year = yearInput.getText();
		int newIndex;
		
		if (name=="" && artist=="" && album=="" && year==""){
			Alert emptyInput = new Alert(AlertType.INFORMATION);
			emptyInput.setTitle("Invalid Input");
			emptyInput.setHeaderText("You did not enter any text. No song were added.");
			emptyInput.setContentText("Click OK to continue");

			emptyInput.showAndWait();
		} else {
			newIndex = addSong(name, artist, album, year);
			refreshLVStrings(playList);
			lv.setItems(playListStrings);
			lv.getSelectionModel().select(newIndex);
		}
	}
	public void refreshLVStrings(ObservableList<Song> playList){
		playListStrings.clear();
		for (Song temp:playList){
			playListStrings.add(temp.getName());
		}
	}
	public void editAction(ActionEvent e){
		
	}
	public void deleteAction(ActionEvent e){
		
	}
	public int addSong(String name,String artist, String album, String year){
		
		Song song = new Song(name, artist, album, year);
		Song temp;
		int j = 0;
		if(playList.isEmpty()){
			playList.add(song);
		}
		else{
			for (int i=0;i<playList.size();i++){
			 for (j=1;j<(playList.size()-j);j++){
	                if (playList.get(j-1).compareTo(playList.get(j) )>=0)
	                {
	                    temp=playList.get(j-1);
	                    playList.remove(j-1);
	                    playList.add(j, temp);
	                } else if (playList.get(j-1).compareTo(playList.get(j) )==0){
	                	Alert emptyInput = new Alert(AlertType.INFORMATION);
	        			emptyInput.setTitle("Invalid Input");
	        			emptyInput.setHeaderText("The song is already in the list.");
	        			emptyInput.setContentText("Click OK to continue");
	                }
	            }
			}
		}
		return j;
	}
	public void delete(Song a){
		
		if(!playList.isEmpty()){
			int index= lv.getSelectionModel().getSelectedIndex();
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
	
