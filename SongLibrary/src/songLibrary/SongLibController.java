package songLibrary;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongLibController {
	
	@FXML ListView<String> lv;
	@FXML TextField nameInput;
	@FXML TextField artistInput;
	@FXML TextField albumInput;
	@FXML TextField yearInput;
	@FXML TextArea detailBox;
	
	private ObservableList<Song> playList= 
			FXCollections.observableArrayList();
	private ObservableList<String> playListStrings=
			FXCollections.observableArrayList();
	
	public void start(Stage mainStage){
		refreshLV(0);
	}
	public void refreshLV(int index){
		refreshLVStrings(playList);
		lv.setItems(playListStrings);
		lv.getSelectionModel().select(index);
	}
	public void refreshLVStrings(ObservableList<Song> playList){
		playListStrings.clear();
		for (Song temp:playList){
			playListStrings.add(temp.getName());
		}
	}
	@FXML
	public void addAction(ActionEvent e){
		String name = nameInput.getText();
		String artist = artistInput.getText();
		String album = albumInput.getText();
		String year = yearInput.getText();
		int newIndex = 0;
		
		if (name.isEmpty() && artist.isEmpty() && album.isEmpty() && year.isEmpty()){
			Alert emptyInput = new Alert(AlertType.INFORMATION);
			emptyInput.setTitle("Invalid Input");
			emptyInput.setHeaderText("You did not enter any text. No song were added.");
			emptyInput.setContentText("Click OK to continue");

			emptyInput.showAndWait();
		} else {
			newIndex = addSong(name, artist, album, year);
		}
		refreshLV(newIndex);
	}
	public void editAction(ActionEvent e){
		String name = nameInput.getText();
		String artist = artistInput.getText();
		String album = albumInput.getText();
		String year = yearInput.getText();
		
		int selectedIndex = lv.getSelectionModel().getSelectedIndex();
		
		edit(selectedIndex, name, artist, album, year);
	}
	public void deleteAction(ActionEvent e){
		
	}
	public void onLVClick(ActionEvent e){
		int selectedIndex = lv.getSelectionModel().getSelectedIndex();
		Song a = playList.get(selectedIndex);
		String name = a.getName();
		String artist = a.getArtist();
		String album = a.getAlbum();
		String year = a.getYear();
		
		
	}
	public void refreshDetailBox(String name, String artist, String album, String year){
		
	}
	public int addSong(String name,String artist, String album, String year){
		Song song = new Song(name, artist, album, year);
		int dontAdd = 0;
		if(playList.isEmpty()){
			System.out.println("add1");
			playList.add(song);
			return 0;
		} else {
			for (int h=0;h<playList.size();h++){
				int compareName = song.getName().compareTo(playList.get(h).getName());
				if (compareName == 0){
					int compareArtist = song.getArtist().compareTo(playList.get(h).getArtist());
					if (compareArtist==0){
						Alert emptyInput = new Alert(AlertType.INFORMATION);
	        			emptyInput.setTitle("Invalid Input");
	        			emptyInput.setHeaderText("The song is already in the list.");
	        			emptyInput.setContentText("Click OK to continue");
	        			
	        			emptyInput.showAndWait();
	        			dontAdd = 1;
	        			break;
					} else if (compareArtist>0){
						continue;
					} else if (compareArtist<0){
						System.out.println("add compare artist");
						playList.add(h,song);
					}
				} else if (compareName>0){
					continue;
				} else if (compareName<0){
					playList.add(h,song);
					return h;
				}
			}
			if (dontAdd==0){
				playList.add(song);
				return (playList.size()-1);
			}
			return -1;
		}
	}
	public void delete(Song a){
		
		if(!playList.isEmpty()){
			int index= lv.getSelectionModel().getSelectedIndex();
			playList.remove(index);	
		}

	}
	public void edit(int index, String name, String artist, String album, String year){
		Song a = playList.get(index);
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
		
		if (addSong(b.getName(),b.getArtist(),b.getAlbum(),b.getYear()) != -1){
			delete (a);
		}
		refreshLV(index);
	}		
}
	
