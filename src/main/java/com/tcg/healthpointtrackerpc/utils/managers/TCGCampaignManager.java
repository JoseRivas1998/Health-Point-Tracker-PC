package com.tcg.healthpointtrackerpc.utils.managers;

import com.tcg.healthpointtrackerpc.TCGConstants;
import com.tcg.healthpointtrackerpc.utils.TCGCampaign;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseR on 6/20/2017.
 */
public class TCGCampaignManager {

    List<TCGCampaign> campaigns;

    public TCGCampaignManager() {
        campaigns = new ArrayList<>();
    }

    private static String defaultJsonString() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        jsonObject.put("campaigns", array);
        return jsonObject.toString();
    }

    public String jsonString() {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        for(int i = 0; i < campaigns.size(); i++) {
            array.put(campaigns.get(i).toJSONObject());
        }
        jsonObject.put("campaigns", array);
        return jsonObject.toString();
    }

    public JSONObject toJSON() {
        if(campaigns.size() > 0) {
            return new JSONObject(jsonString());
        } else {
            return new JSONObject(defaultJsonString());
        }
    }

    public void loadCampaigns() {
        File campaigns_file = new File(FileManager.localAppDataFolder() + File.separator + TCGConstants.campaigns_file_name);
        campaigns.clear();
        if(campaigns_file.exists()) {
            try {
                JSONObject jsonObject = FileManager.loadJSONFile(campaigns_file);
                JSONArray jsonArray = jsonObject.getJSONArray("campaigns");
                for(int i = 0; i < jsonArray.length(); i++) {
                    campaigns.add(new TCGCampaign(jsonArray.getJSONObject(i)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveCampaigns();
        }
    }

    public void saveCampaigns() {
        File campaigns_file = new File(FileManager.localAppDataFolder() + File.separator + TCGConstants.campaigns_file_name);
        try {
            FileManager.saveJSONFile(campaigns_file, toJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCampaign(String campaignTitle, int baseHP) {
        campaigns.add(new TCGCampaign(campaignTitle, baseHP));
    }

    public void removeCampaign(int i) {
        campaigns.remove(i);
    }

    public int size() {
        return campaigns.size();
    }

    public int getBaseHP(int i) {
        return campaigns.get(i).getBaseHP();
    }

    public void setBaseHP(int i, int baseHP) {
        campaigns.get(i).setBaseHP(baseHP);
    }

    public String getCampaignTitle(int i) {
        return campaigns.get(i).getCampaignTitle();
    }

    public void setCampaignTitle(int i, String campaignTitle) {
        campaigns.get(i).setCampaignTitle(campaignTitle);
    }

    public int getCurrentHP(int i) {
        return campaigns.get(i).getCurrentHP();
    }

    public void setCurrentHP(int i, int currentHP) {
        campaigns.get(i).setCurrentHP(currentHP);
    }

}
