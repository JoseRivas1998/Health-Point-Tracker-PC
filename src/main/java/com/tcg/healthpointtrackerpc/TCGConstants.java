package com.tcg.healthpointtrackerpc;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by JoseR on 6/20/2017.
 */
public class TCGConstants {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 420;

    public static final String TITLE = "Health Point Tracker PC";

    public static final String VERSIONSTRING = "0.1-ALPHA";
    public static final int VERSIONCODE = 1;

    public static Dimension screenSize;

    public static Font fontAwesome;
    public static Font fontAwesomeLarge;

    public static Font largeDefault;

    public static Stage mainStage;

    public static final String campaigns_file_name = "campaigns.json";

    public static Label iconText(String icon) {
        Label iconLabel = new Label(icon);
        iconLabel.setFont(TCGConstants.fontAwesome);
        return iconLabel;
    }

    public static Label largeIconText(String icon) {
        Label iconLabel = new Label(icon);
        iconLabel.setFont(TCGConstants.fontAwesomeLarge);
        return iconLabel;
    }

    public static String noSpace(String string) {
        return string.replaceAll("\\s+","");
    }

    public static void openURL(String url) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL(url).toURI());
    }

    public static float clamp(float n, float min, float max) {
        float r = n;
        if(r < min) {
            r = min;
        }
        if(r > max) {
            r = max;
        }
        return r;
    }

    public static int clamp(int n, int min, int max) {
        int r = n;
        if(r < min) {
            r = min;
        }
        if(r > max) {
            r = max;
        }
        return r;
    }

    public static boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

}
