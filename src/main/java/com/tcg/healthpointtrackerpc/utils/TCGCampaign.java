package com.tcg.healthpointtrackerpc.utils;

import org.json.JSONObject;

/**
 * Created by JoseR on 6/20/2017.
 */
public class TCGCampaign {
    private String campaignTitle;
    private int baseHP;
    private int currentHP;

    public TCGCampaign(String campaignTitle, int baseHP, int currentHP) {
        this.baseHP = baseHP;
        this.campaignTitle = campaignTitle;
        this.currentHP = currentHP;
    }

    public TCGCampaign(String campaignTitle, int baseHP) {
        this(campaignTitle, baseHP, baseHP);
    }

    public TCGCampaign(JSONObject campaign) {
        this(campaign.getString("campaignTitle"), campaign.getInt("baseHP"), campaign.getInt("currentHP"));
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("campaignTitle", campaignTitle);
        jsonObject.put("baseHP", baseHP);
        jsonObject.put("currentHP", currentHP);
        return jsonObject;
    }

    @Override
    public String toString() {
        return toJSONObject().toString();
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
}
