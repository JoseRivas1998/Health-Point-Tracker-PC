package com.tcg.healthpointtrackerpc;

import com.tcg.healthpointtrackerpc.components.TCGMainPane;
import com.tcg.healthpointtrackerpc.utils.managers.FileManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by JoseR on 6/20/2017.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        if(!FileManager.applicationFolderExists()) {
            FileManager.createApplicationFolder();
        }

        TCGConstants.screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        TCGConstants.fontAwesome = Font.loadFont(FileManager.getInternalFile("font/fontawesome.ttf"), Font.getDefault().getSize());
        TCGConstants.fontAwesomeLarge = Font.loadFont(FileManager.getInternalFile("font/fontawesome.ttf"), 36);

        TCGConstants.largeDefault = Font.font(Font.getDefault().getSize() * 2);

        TCGMainPane mainPane = new TCGMainPane();

        Scene scene = new Scene(mainPane, TCGConstants.WIDTH, TCGConstants.HEIGHT);

        TCGConstants.mainStage = primaryStage;

        TCGConstants.mainStage.setScene(scene);

        Image[] icons = {
                new Image(FileManager.getInternalFile("images/icons/16.png")),
                new Image(FileManager.getInternalFile("images/icons/48.png")),
                new Image(FileManager.getInternalFile("images/icons/72.png")),
                new Image(FileManager.getInternalFile("images/icons/96.png")),
                new Image(FileManager.getInternalFile("images/icons/144.png")),
                new Image(FileManager.getInternalFile("images/icons/192.png"))
        };

        TCGConstants.mainStage.getIcons().addAll(icons);
        TCGConstants.mainStage.setResizable(false);
        TCGConstants.mainStage.setTitle(TCGConstants.TITLE);
        TCGConstants.mainStage.show();

    }


}
