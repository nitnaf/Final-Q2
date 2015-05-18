package ch.makery.address;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private AnchorPane calculatorUI;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Calculator");
      
        // Somehow Load Months and Years comboBoxes
        
        showCalculatorUI();
    }

	public void showCalculatorUI(){
		
	    try {
	        
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/CalculatorUI.fxml"));
	        calculatorUI = (AnchorPane) loader.load();

	       
	        Scene scene = new Scene(calculatorUI);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

