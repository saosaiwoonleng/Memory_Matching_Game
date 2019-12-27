package com.example.MemoryMatchingGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownload extends AsyncTask<ArrayList<String>, Integer, ArrayList<String>> {
    private ICallback callback = null;
    public ImageDownload(ICallback callback) {
        this.callback = callback;
    }

    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... urls) {
        long imageLen = 0;
        long totalSoFar = 0;
        int readLen = 0;
        Bitmap bitmap = null;

        ArrayList<String> allUrls = urls[0];
            try {
                for (int i = 0; i < allUrls.size(); i++) {
                        URL url = new URL(urls[0].get(i));
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();

                        imageLen = conn.getContentLength();
                        byte[] data = new byte[1024];

                        InputStream in = url.openStream();
                        BufferedInputStream bufIn = new BufferedInputStream(in, 2048);
                        OutputStream out = new FileOutputStream(urls[1].get(0) + (i + 1) + ".jpg");

                        while ((readLen = bufIn.read(data)) != -1) {
                            totalSoFar += readLen;
                            out.write(data, 0, readLen);
                        }

                        File file = new File(urls[1].get(0) + (i + 1) + ".jpg");
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                        publishProgress(i+1);
                        bufIn.close();
                        in.close();
                        out.close();
                        conn.disconnect();
                    if(isCancelled()){
                        break;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int test = values[0];
        System.out.println(test);
        if (callback != null)
            callback.onAsyncTaskProgress(values[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<String> string) {
        if(callback!=null){
            callback.onDownloadFinish();
        }

    }
    @Override
    protected void onCancelled(){
        if(callback!=null){
            callback.cancelTask();
        }
    }

    public interface ICallback {
        void onDownloadFinish();
        void onAsyncTaskProgress(int progress);
        void cancelTask();
    }




}
