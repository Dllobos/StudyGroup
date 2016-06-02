package com.studygroup.studygroup.controllers;

/**
 * @author:
 */
public class HttpPost /**extends AsyncTask<String, Void, String>*/ {

    //private Context context;

    /**
     * Constructor
     */
    /**
    public HttpPost(Context context) {
        this.context = context;
    }// HttpGet(Context context)

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            return new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (MalformedURLException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (ProtocolException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent("httpData").putExtra("data", result);
        context.sendBroadcast(intent);
    }*/
}// HttpPost