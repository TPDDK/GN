package com.testapplication.tdi.gn;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JSONparser {
    private JSONObject jsonObj;
    private ArrayList<GitHubItem> gitHubUserList = new ArrayList<>();

    public ArrayList<GitHubItem> parse(String jsonString) {
        if(jsonString != null) {
            try {
                jsonObj = new JSONObject(jsonString);
                JSONArray items = jsonObj.getJSONArray("items");

                for(int i=0;i<items.length();i++) {
                    JSONObject contact = items.getJSONObject(i);
                    String user = contact.getString("url");
                    GitHubItem item = new GitHubItem(user);
                    gitHubUserList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gitHubUserList;
    }
}
