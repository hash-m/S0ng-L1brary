/*
* Ishan Pal
* Javed Shah
*/

package songLibrary;

import java.util.Collections;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	public int confirmationDialog(String name, String action){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure you want to "+action+" "+name+" in the playlist?");
		alert.setContentText("Click OK to continue.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return 1;
		} else {
		    return 0;
		}
	}
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
		if(newIndex != -1){
			refreshDetailBox(newIndex);
		}
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
		int selectedIndex = lv.getSelectionModel().getSelectedIndex();
		Song a = playList.get(selectedIndex);
		delete(a);
		if (!playList.isEmpty()){
			selectedIndex = lv.getSelectionModel().getSelectedIndex();
			refreshDetailBox(selectedIndex);
		}
	}
	public void onListViewClick(MouseEvent e){
		if (!playList.isEmpty()){
			int selectedIndex = lv.getSelectionModel().getSelectedIndex();
			refreshDetailBox(selectedIndex);
		}
	}
	public void refreshDetailBox(int selectedIndex){
		Song a = playList.get(selectedIndex);
		String name = a.getName();
		String artist = a.getArtist();
		String album = a.getAlbum();
		String year = a.getYear();
		detailBox.setText("Name: "+name+"\t\t\t\t\t\t\t\t"+album+"("+year+")"+"\n\n\n"+"Artist: "+artist);
	}
	public int addSong(String name,String artist, String album, String year){
		Song song = new Song(name, artist, album, year);
		int dontAdd = 0;
		if(playList.isEmpty()){
			if (confirmationDialog(song.getName(),"add")==1){
				playList.add(song);
				return 0;
			} else {
				return 0;
			}
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
						if (confirmationDialog(song.getName(),"add")==1){
							playList.add(h,song);
							return 0;
						} else {
							return 0;
						}
					}
				} else if (compareName>0){
					continue;
				} else if (compareName<0){
					if (confirmationDialog(song.getName(),"add")==1){
						playList.add(h,song);
						return h;
					} else {
						return 0;
					}
				}
			}
			if (dontAdd==0){
				if (confirmationDialog(song.getName(),"add")==1){
					playList.add(song);
					return (playList.size()-1);
				} else {
					return (playList.size()-1);
				}
			}
			return -1;
		}
	}
	public void delete(Song a){
		if(!playList.isEmpty()){
			int index= lv.getSelectionModel().getSelectedIndex();
			if (confirmationDialog(playList.get(index).getName(),"delete")==1){
				if(index==0){
					playList.remove(index);
				} else {
					playList.remove(index);
				}
				if(!playList.isEmpty()){
					if(playList.size()-1<index){
						refreshLV(index-1);
					} else if (playList.size()-1>index){
						refreshLV(index+1);
					} else {
						refreshLV(index);
					}
				} else {
					refreshLV(0);
				}
			}
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
		if (confirmationDialog(b.getName(), "edit") == 1){
			if (addSong(b.getName(),b.getArtist(),b.getAlbum(),b.getYear()) != -1){
				delete (a);
			}
		}
		
		refreshLV(index);
	}		
}
	
