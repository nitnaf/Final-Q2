package ch.makery.address.view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import org.apache.poi.ss.formula.functions.FinanceLib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UIController implements Initializable {
	// Saves the two values so they can be easily compared later on
	private double save18;
	private double save36;
	private double payment;
	
	//OUTPUTS
	@FXML
	private Label housingPayment;
	@FXML
	private Label hpOther;
	@FXML
	private Label maxPayment;
	@FXML
	private Label mortgageFinanced;
	
	// INPUTS
	@FXML
	private TextField grossIncome;
	@FXML
	private TextField monthlyDebt;
	@FXML
	private TextField mortgageIR;
	@FXML
	private TextField downPayment;
	@FXML
	private ComboBox term;
	

	public void loadBox() {
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "10",
			        "15",
			        "30");
		
		term.setItems(options);
	}

	public void initialize(URL url, ResourceBundle rb) {

		loadBox();
	}
	
	public void calcHousingPayment(){
		double income = Double.parseDouble(grossIncome.getText());
		double answer = (income/12)*.18;
		this.save18 = answer;
		housingPayment.setText("$" + Double.toString(answer));
	}

	public void calcHPOther(){
		double income = Double.parseDouble(grossIncome.getText());
		double answer = (((income/12)*.36) - Double.parseDouble(monthlyDebt.getText())) - Double.parseDouble(downPayment.getText());
		this.save36 = answer;
		hpOther.setText("$" + Double.toString(answer));
	}
	
	public void showMaxPayment(){
		if (this.save18 < this.save36){
			this.payment = this.save18;
			maxPayment.setText("$" + Double.toString(this.save18));
		} else {
			this.payment = this.save36;
			maxPayment.setText("$" + Double.toString(this.save36));
		}
	}
	
	public void calcMortgageFinanced(){
		DecimalFormat df = new DecimalFormat("#.##");
		double IRinput = Double.parseDouble((mortgageIR.getText()));
		if (IRinput > 1){
			IRinput = IRinput / 100;
		}
		double numberOfPayments = (Double.parseDouble((String) term.getValue())) * 12;
		double answer = (-1 * FinanceLib.pv(IRinput/12, numberOfPayments, this.payment, 0, false));
		mortgageFinanced.setText("$" + df.format(answer));
	}
	
	public void buttonPressed(){
		calcHousingPayment();
		calcHPOther();
		showMaxPayment();
		calcMortgageFinanced();
		
	}
	
	

}
