package com.example.MemoryMatchingGame;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ImageLoadTask extends AsyncTask<String, Integer, String> {
    private ICallback callback = null;


    public ImageLoadTask(ICallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                URLConnection con = url.openConnection();
                String encode = con.getContentEncoding();
                if (encode == null) {
                    encode = "ISO-8859-1";
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), encode));
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    String str;
                    while ((str = bufferedReader.readLine()) != null) {
                        stringBuilder.append(str);
                        stringBuilder.append("\n");
                    }
                } finally {
                    bufferedReader.close();
                }
                String html = stringBuilder.toString();
                return html;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    protected void onPostExecute(String html) {
        if(callback!=null){
            callback.onProcessFinish(html);
        }
    }

    public interface ICallback {
        void onProcessFinish(String output);
    }

}