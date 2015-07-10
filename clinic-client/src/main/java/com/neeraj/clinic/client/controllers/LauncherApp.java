package com.neeraj.clinic.client.controllers;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neeraj.core.ui.CommonCallingBase;

public class LauncherApp extends Application {

	Logger logger = Logger.getLogger(LauncherApp.class);

	/**
	 * Class to start the application
	 */
	@SuppressWarnings("restriction")
	public static void main(String[] args) {

		new ClassPathXmlApplicationContext("springClient.xml");
		launch();

	}

	@Override
	public void start(Stage loginScreenStage) throws Exception {

		MenuScreenController menuScreenController = new MenuScreenController();
		CommonCallingBase base=new CommonCallingBase(loginScreenStage, menuScreenController, "MenuScreen.fxml", "heading.mainScreen", "mainScreenIcon.png");
		
	}

}
