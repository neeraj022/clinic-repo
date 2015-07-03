package com.neeraj.ui;

import java.io.IOException;
import java.util.Locale;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.apache.log4j.Logger;

import com.neeraj.core.spring.ApplicationContextProvider;

public class CommonCallingBase {
	private Logger logger = Logger.getLogger(CommonCallingBase.class);

	public CommonCallingBase(Stage stage, Object controller, String fxmlName, String heading, String iconName) {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/fxml/"+fxmlName));
		fxmlLoader.setController(controller);
		Scene scene = null;
		try {
			scene = new Scene((Parent) fxmlLoader.load());
		} catch (IOException e) {
			logger.error("try Catch Error"+e.getMessage());
		}
		scene.getStylesheets().add(ApplicationContextProvider.getMessage("defaultCssPath", null, null));
		stage.setScene(scene);
		stage.setTitle(ApplicationContextProvider.getMessage(heading, null, Locale.US));
		if (iconName != null) {
			stage.getIcons().add(new Image("/images/"+iconName));
		}
		stage.show();
	}

}
