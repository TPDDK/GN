package com.testapplication.tdi.gn;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JSONparser {
    JSONObject jsonObj;
    ArrayList<GithubItem> gitHubUserList = new ArrayList<>();

    public ArrayList<GithubItem> parse(String jsonString) {
        if(jsonString != null) {
            try {
                jsonObj = new JSONObject(jsonString);
                JSONArray contacts = jsonObj.getJSONArray("items");

                for(int i=0;i<contacts.length();i++) {
                    JSONObject contact = contacts.getJSONObject(i);
                    String user = contact.getString("url");
                    GithubItem item = new GithubItem(user);
                    gitHubUserList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gitHubUserList;
    }
}
