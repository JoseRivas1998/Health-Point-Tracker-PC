package com.tcg.healthpointtrackerpc.components;

import com.tcg.healthpointtrackerpc.TCGConstants;
import com.tcg.healthpointtrackerpc.utils.UpdateNavigationListener;
import com.tcg.healthpointtrackerpc.utils.managers.TCGCampaignManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * Created by JoseR on 6/20/2017.
 */
public class TCGAddCampaignScreen extends VBox {

    private TextField title, basehp;

    private TCGCampaignManager campaignManager;

    public TCGAddCampaignScreen(UpdateNavigationListener updateNavigationListener) {
        super(5);
        Label titleLabel = new Label("Campaign Title");
        titleLabel.setFont(TCGConstants.largeDefault);

        title = new TextField();
        title.setPromptText("Campaign Title");

        Label basehpLabel = new Label("Base HP");
        basehpLabel.setFont(TCGConstants.largeDefault);

        basehp = new TextField();
        basehp.setPromptText("Base HP");

        campaignManager = new TCGCampaignManager();
        campaignManager.loadCampaigns();

        Button add_campaign = new Button("Add Campaign");
        add_campaign.setOnAction(event -> {
            if(title.getText().length() > 0) {
                if(basehp.getText().length() > 0) {
                    if(TCGConstants.isInteger(basehp.getText())) {
                        String campaignTitle = title.getText();
                        int baseHP = new Integer(basehp.getText());
                        ButtonType yesbuttontype = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType nobuttontype = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setTitle("Add Campaign");
                        alert.setHeaderText(null);
                        alert.setContentText(String.format("Add campaign, \"%s\", with base HP of %d?", campaignTitle, baseHP));
                        alert.initOwner(TCGConstants.mainStage);
                        alert.getDialogPane().getButtonTypes().removeAll();
                        alert.getDialogPane().getButtonTypes().addAll(nobuttontype, yesbuttontype);
                        if(alert.showAndWait().get() == yesbuttontype) {
                            campaignManager.addCampaign(campaignTitle, baseHP);
                            campaignManager.saveCampaigns();
                            updateNavigationListener.updateNavigation();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Add Campaign");
                        alert.setHeaderText(null);
                        alert.setContentText("Base HP must be an integer");
                        alert.initOwner(TCGConstants.mainStage);
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Add Campaign");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a title and base HP");
                    alert.initOwner(TCGConstants.mainStage);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Campaign");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a title and base HP");
                alert.initOwner(TCGConstants.mainStage);
                alert.showAndWait();
            }


        });

        getChildren().addAll(titleLabel, title, basehpLabel, basehp, add_campaign);
        setPadding(new Insets(10));
    }
}
