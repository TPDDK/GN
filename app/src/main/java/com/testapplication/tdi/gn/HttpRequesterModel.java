package com.testapplication.tdi.gn;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class HttpRequesterModel extends AsyncTask<String, Void, String> {
    private String mUrl;
    private final JSONparser mParser;
    private ModelListener HttpRequestListener = null;

    public interface ModelListener {
        void OnHttpRequestFinished(ArrayList<GithubItem> result);
    }

    public HttpRequesterModel() {
        mParser = new JSONparser();
    }

    public void SetModelListener(ModelListener listener) {
        HttpRequestListener = listener;
    }

    public void requestData(String url) {
        mUrl = url;
        execute();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            URL url = new URL(mUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "SurfAdvisor/1.0 Android");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<GithubItem> resultData = mParser.parse(result);
        if (HttpRequestListener != null) {
            HttpRequestListener.OnHttpRequestFinished(resultData);
        }
    }
}
