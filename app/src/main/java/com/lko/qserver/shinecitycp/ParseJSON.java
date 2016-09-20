package com.lko.qserver.shinecitycp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by QServer on 5/26/2016.
 */
public class ParseJSON {
    public static String[] ids;
    public static String[] names;
    public static String[] emails;


    public static final String KEY_ID = "ContactType";
    public static final String KEY_NAME = "FirstName";
    public static final String KEY_EMAIL = "Mobile";



    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public void parseJSON(){

        try {

            JSONArray jsonArray = new JSONArray(json);

            for(int i= 0;i<=jsonArray.length();i++) {

                ids = new String[jsonArray.length()];
                names = new String[jsonArray.length()];
                emails = new String[jsonArray.length()];


                    JSONObject jo = jsonArray.getJSONObject(i);
                    ids[i] = jo.getString(KEY_ID);
                    names[i] = jo.getString(KEY_NAME);
                    emails[i] = jo.getString(KEY_EMAIL);
                }

            } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
    }
