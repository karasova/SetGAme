package com.example.setgame.Server;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.example.setgame.RegisterResponse;
import com.example.setgame.Response;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;

public class Registration extends AsyncTask<Pair<String, Function<Response, Void>>, Void, Response>  {
    String SERVER_URL = "http://194.176.114.21:8050";
    URL url;
    Response response;
    Function<Response, Void> handler;

    public RegisterResponse register(String request)  {
        try {
            url = new URL(SERVER_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            OutputStream out = urlConnection.getOutputStream();
            out.write(request.getBytes());

            Gson gson = new Gson();
            RegisterResponse response = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), RegisterResponse.class);
            urlConnection.disconnect();
            return response;
        } catch (IOException e) {
            return new RegisterResponse();
        }
    }

    @Override
    protected Response doInBackground(Pair<String, Function<Response, Void>>... pairs) {
        handler = pairs[0].second;
        response = register(pairs[0].first);
        return response;
    }

    @Override
    protected void onPostExecute(Response response) {
        handler.apply(response);
    }

//
//    public SetResponse take_set(String request) throws IOException {
//        url = new URL(SERVER_URL);
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setDoOutput(true);
//        OutputStream out = urlConnection.getOutputStream();
//        out.write(request.getBytes());
//
//        Gson gson = new Gson();
//        SetResponse response = gson.fromJson(new InputStreamReader(urlConnection.getInputStream()), SetResponse.class);
//        urlConnection.disconnect();
//        return response;
//    }


}

