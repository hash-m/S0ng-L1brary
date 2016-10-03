package songLibrary;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.fxml.*;
import songLibrary.SongLibController;

public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					getClass().getResource("/MainUI.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			SongLibController songLibController = 
					loader.getController();
			songLibController.start(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Shwasty Song Collection");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}