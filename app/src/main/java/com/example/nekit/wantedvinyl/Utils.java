package com.example.nekit.wantedvinyl;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    public static String sendGet(String url) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) return null;
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String substring(String str, String val1, String val2) {
        return str.substring(str.indexOf(val1), str.indexOf(val2)+val2.length());
    }

    public static String stripArgs(String str) {
        str = str.replace("<td class=\"DataValue TableDataValue\">", "");
        str = str.replace("<td class=\"DataValue TableDataValue\"  style=\"text-align: right\">", "");
        str = str.replace("</td>", "");
        return str;
    }
}
