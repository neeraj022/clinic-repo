package com.neeraj.clinic.client.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

import com.neeraj.core.spring.ApplicationContextProvider;
import com.neeraj.core.ui.CommonCallingBase;

public class MenuScreenController implements Initializable {

	@FXML
	private Label mainScreenL;
	@FXML
	private Button inventoryButton;
	@FXML
	private Button settingsButton;

	Logger logger = Logger.getLogger(MenuScreenController.class);

	public void initialize(URL arg0, ResourceBundle arg1) {
		setNames();
		setActions();

	}

	/*
	 * set Names for labels, button, tables etc..
	 */
	private void setNames() {
		mainScreenL.setText(ApplicationContextProvider.getMessage("label.mainScreen", null, null, Locale.US));
	}

	/*
	 * set button clicks, table clicks etc
	 */
	private void setActions() {
		inventoryButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				// open inventory screen
				Stage inventoryStage=new Stage();
				InventoryManagementController inventoryManagementController=new InventoryManagementController();
				CommonCallingBase base=new CommonCallingBase(inventoryStage, inventoryManagementController, "InventoryManagement.fxml", "heading.inventoryScreen", null);
				
			}

		});
	}

	/*
	 * @FXML private TextArea testArea;
	 * 
	 * @FXML private Button testButton;
	 * 
	 * @FXML private Button writeButton;
	 * 
	 * public void initialize(URL arg0, ResourceBundle arg1) { testButton.setOnAction(new EventHandler<ActionEvent>() {
	 * 
	 * public void handle(ActionEvent arg0) {
	 * 
	 * try { testArea.clear(); testMethod(); } catch (ClassNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * });
	 * 
	 * writeButton.setOnAction(new EventHandler<ActionEvent>()
	 * 
	 * {
	 * 
	 * public void handle(ActionEvent arg0) { writeMethod();
	 * 
	 * }});
	 * 
	 * testButton.setText("TEST"); testArea.setPromptText("Result will be displayed here"); writeButton.setText("WRITE TEST");
	 * 
	 * }
	 * 
	 * private void writeMethod() { GlobalInteractionClient global = (GlobalInteractionClient)
	 * ApplicationContextProvider.getBean("globalInteractionClient"); global.setDtoName("com.neeraj.clinic.model.People"); String urlMapping =
	 * "people/"; MyResponse myReponse=null; PeopleMs people=null; try { people = testMethod(); } catch (ClassNotFoundException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } //people.setFirstName(people.getFirstName()+" Edited"); People people=new People();
	 * people.setFirstName("Shams"); people.setLastName("Ansari"); people.setDelFlg('N'); //people.setLastName(people.getLastName()+"edited");
	 * 
	 * //System.out.println(Restrictions.and(Restrictions.le("le1", "adsf"), Restrictions.and(Restrictions.eq("innerAnd1",
	 * "Neeraj"),Restrictions.or(Restrictions.eq("orEq1", "Neeraj"), Restrictions.eq("orEq2", "Neeraj")))).toString());
	 * 
	 * //queryBuilder.addCondition(Restrictions.eq("firstName", "Neeraj")); myReponse = global.write(urlMapping,HttpMethod.PUT,people); PeopleMs
	 * people1 = (PeopleMs) myReponse.getData().get(0); testArea.setText(people1.toString());
	 * 
	 * }
	 * 
	 * private PeopleMs testMethod() throws ClassNotFoundException { JavaMailSender mailSender = (JavaMailSender)
	 * ApplicationContextProvider.getBean("mailSender");
	 * 
	 * MimeMessage email=new MimeMessage(); email.set("neeraj.yadav@cmcltd.com"); email.setSubject("Spring email testing");
	 * email.setText("HEHE its working");
	 * 
	 * 
	 * mailSender.send(new MimeMessagePreparator() { public void prepare(MimeMessage mimeMessage) throws MessagingException { MimeMessageHelper
	 * message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); message.setFrom("neeraj.yadav@cmcltd.com");
	 * message.setTo("neeraj.yadav@cmcltd.com"); message.setSubject("A file for you"); message.setText("<b>See the attached</b>", true);
	 * message.addAttachment( "mainScreenIcon.png", new File(
	 * "D:/neeraj/ClinicProject/WorkSpace/clinic-client/src/main/resource/images/mainScreenIcon.png")); } });
	 * 
	 * GlobalInteractionClient global = (GlobalInteractionClient) ApplicationContextProvider.getBean("globalInteractionClient");
	 * global.setDtoName("com.neeraj.clinic.model.People"); String urlMapping = "people/"; QueryBuilder queryBuilder=new QueryBuilder();
	 * queryBuilder.addCondition("id", "8"); MyResponse myReponse = global.read(urlMapping,queryBuilder); PeopleMs people = (PeopleMs)
	 * myReponse.getData().get(0); Date myDate=people.getInsDttm();
	 * testArea.setText(myReponse.getData().size()+"----"+people.toString()+"    My date: "+myDate.toString()); return people; }
	 */

}
