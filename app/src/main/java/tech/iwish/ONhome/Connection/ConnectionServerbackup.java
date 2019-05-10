package tech.iwish.ONhome.Connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by iwish on 11/30/2016.
 */
public class ConnectionServerbackup extends AsyncTask<String,String,String> {

    ProgressDialog pdLoading;
    HttpURLConnection conn;
    URL url = null;
    int READ_TIMEOUT = 15000;
    int CONNECTION_TIMEOUT = 10000;
    String REQUESTED_METHOD = "POST";
    Uri.Builder builder = null;
    String rtn = "FALSE";
    public AsyncResponse delegate = null;


    public ConnectionServerbackup(AsyncResponse delegate){
        this.delegate = delegate;
        this.builder = new Uri.Builder();

    }


    @Override
    public String doInBackground(String... string) {
        try{
            this.conn = (HttpURLConnection)this.url.openConnection();
            this.conn.setReadTimeout(this.READ_TIMEOUT);
            this.conn.setConnectTimeout(this.CONNECTION_TIMEOUT);
            this.conn.setRequestMethod(this.REQUESTED_METHOD);


          //  if(this.isFileUpload)
            //    this.file_uploading();

            this.conn.setDoInput(true);
            this.conn.setDoOutput(true);
            Log.e("conn log",this.conn.toString());
            String query = (builder != null) ? this.builder.build().getEncodedQuery() : "";

            OutputStream os = conn.getOutputStream();
           // Log.e("os",os.toString());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            writer.write(query);
            //Log.e("writer",writer.toString());
            writer.flush();
            writer.close();
            os.close();
            this.conn.connect();




        }catch (IOException e)
        {
            e.printStackTrace();
        }

        try{
                int response_code = this.conn.getResponseCode();
            Log.e("response_code",String.valueOf(response_code));
                if(response_code == HttpURLConnection.HTTP_OK)
                {
                        InputStream input = this.conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        // Pass data to onPostExecute method
                        this.rtn = result.toString();
                    Log.e("result",this.rtn);
                }
            else
                {
                    InputStream input = this.conn.getErrorStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    this.rtn = result.toString();
                    Log.e("result",this.rtn);
                   this.rtn = "FALSE";
                }
        } catch (IOException e)
        {
            e.printStackTrace();

        }finally {
            this.conn.disconnect();
        }
        return rtn;
    }
    @Override
    public void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread
        this.pdLoading.setMessage("\tLoading...");
        this.pdLoading.setCancelable(false);
        this.pdLoading.show();

    }

    @Override
    public void onPostExecute(String result){
        super.onPostExecute(result);
        this.pdLoading.dismiss();
        delegate.processFinish(result);
      //  Log.e("data",result);

    }

    public void set_current_activity(Context context)
    {
        this.pdLoading = new ProgressDialog(context);
    }

    public void set_url(String url)
    {
            try{
                 this.url = new URL(url);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();

            }
        }
    public void readTimeout(int timeout){

        this.READ_TIMEOUT = timeout;
    }

    public void connectionTimeout(int timeout){

        this.CONNECTION_TIMEOUT = timeout;
    }

    public void requestedMethod(String method)
    {
        this.REQUESTED_METHOD = method;
    }

    public void buildParameter(String key, String parameter)
    {
        this.builder.appendQueryParameter(key,parameter);
    }



    public interface AsyncResponse {
        void processFinish(String output);
    }

    public void setRequestProperty(String Key, String Value)
    {
        this.conn.setRequestProperty(Key,Value);
    }








}
