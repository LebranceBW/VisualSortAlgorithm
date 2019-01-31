package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader a = new FXMLLoader(getClass().getResource("/resources/MainView.fxml"));
			Parent root = a.load();
			primaryStage.setScene(new Scene(root));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Main.launch(args);
	}
}
