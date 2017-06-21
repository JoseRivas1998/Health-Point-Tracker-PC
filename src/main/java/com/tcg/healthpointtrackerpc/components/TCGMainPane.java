package com.tcg.healthpointtrackerpc.components;

import com.tcg.healthpointtrackerpc.utils.managers.TCGCampaignManager;
import javafx.scene.layout.BorderPane;

/**
 * Created by JoseR on 6/20/2017.
 */
public class TCGMainPane extends BorderPane {

    private TCGCampaignManager campaignManager;
    private TCGNavigationPane tcgNavigationPane;

    public TCGMainPane() {
        super();
        campaignManager = new TCGCampaignManager();
        campaignManager.loadCampaigns();
        tcgNavigationPane = new TCGNavigationPane();
        tcgNavigationPane.setOnCampaignSelected(id -> {
            System.out.println(id);
        });
        tcgNavigationPane.setOnSettingSelected(id -> {
            if(id == 1) {
                setCenter(new TCGAddCampaignScreen(() -> tcgNavigationPane.populateCampaignTree()));
            }
        });
        if(campaignManager.size() > 0) {
            tcgNavigationPane.setCampaignSelected(1);
            tcgNavigationPane.setSettingSelected(0);
        } else {
            tcgNavigationPane.setCampaignSelected(0);
            tcgNavigationPane.setSettingSelected(1);
            setCenter(new TCGAddCampaignScreen(() -> tcgNavigationPane.populateCampaignTree()));
        }
        setLeft(tcgNavigationPane);
    }
}
