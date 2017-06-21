package com.tcg.healthpointtrackerpc.components;

import com.tcg.healthpointtrackerpc.TCGConstants;
import com.tcg.healthpointtrackerpc.utils.managers.FontAwesome;
import com.tcg.healthpointtrackerpc.utils.managers.TCGCampaignManager;
import javafx.geometry.Insets;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by JoseR on 6/21/2017.
 */
public class TCGNavigationPane extends VBox {

    private TCGCampaignManager campaignManager;

    private TreeView<String> campaignTree;
    private TreeItem<String> campaignTreeRootItem;

    private TreeItem<String> settingRootItem;
    private TreeView<String> settingTree;

    private OnItemSelected onCampaignSelected, onSettingSelected;

    public TCGNavigationPane() {
        super(5);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        campaignManager = new TCGCampaignManager();

        setMinWidth(TCGConstants.WIDTH * .25f);

        campaignTreeRootItem = new TreeItem<>("Campaigns", TCGConstants.iconText(FontAwesome.FILE_TEXT_O));
        populateCampaignTree();

        campaignTree = new TreeView<>(campaignTreeRootItem);
        campaignTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int id = campaignTree.getRow(newValue) - 1;
            if(id >= 0) onCampaignSelected.onItemSelected(id);
        });

        settingRootItem = new TreeItem<>("Settings", TCGConstants.iconText(FontAwesome.COG));
        settingRootItem.getChildren().addAll(
          new TreeItem<String>("Add Campaign", TCGConstants.iconText(FontAwesome.PLUS))
        );
        settingTree = new TreeView<>(settingRootItem);
        settingTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int id = settingTree.getRow(newValue);
            onSettingSelected.onItemSelected(id);
        });

        campaignTree.setStyle("-fx-box-border: transparent;-fx-focus-color: transparent;");
        settingTree.setStyle("-fx-box-border: transparent;-fx-focus-color: transparent;");

        getChildren().addAll(campaignTree, settingTree);

    }

    public void populateCampaignTree() {
        campaignManager.loadCampaigns();
        int items = campaignManager.size();
        if(items > 0) {
            TreeItem<String>[] campaingNames = new TreeItem[items];
            for(int i = 0; i < items; i++) {
                campaingNames[i] = new TreeItem<>(campaignManager.getCampaignTitle(i));
            }
            campaignTreeRootItem.getChildren().setAll(campaingNames);
        } else {
            campaignTreeRootItem.getChildren().removeAll();
        }
    }

    public void setOnCampaignSelected(OnItemSelected onCampaignSelected) {
        this.onCampaignSelected = onCampaignSelected;
    }

    public void setOnSettingSelected(OnItemSelected onSettingSelected) {
        this.onSettingSelected = onSettingSelected;
    }

    public void setCampaignSelected(int id) {
        campaignTreeRootItem.setExpanded(id != 0);
        MultipleSelectionModel msm = campaignTree.getSelectionModel();
        msm.select(id);
    }

    public void setSettingSelected(int id) {
        settingRootItem.setExpanded(id != 0);
        MultipleSelectionModel msm = settingTree.getSelectionModel();
        msm.select(id);
    }

    public interface OnItemSelected {

        public void onItemSelected(int id);

    }

}
