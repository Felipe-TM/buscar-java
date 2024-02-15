package br.com.unifil.buscar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GmailSmtpToken {
	  	
		private static String TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
		private static String accessToken = "ya29.a0AfB_byDctvzIYT4Ms2D_Y8eYlsG_tyabZCG-NpX_hHDNiNK6JwVQE6gvayFVEJN8C9Q0snIh300w18hGXF9pqIrR8ePBwAkQ1SnaL1LJhPPAFU6ZC6FT4gicWCWIbyihLbWHPVd0SZqX3cZR_NXYyHTwimZ1eu4iwH9gaCgYKAYYSARESFQHGX2MivH1hYrh_Gwp81DNkfhbEaA0171";

	    private String oauthClientId = "90095561902-ljtpen83s4fa5jhm3icanv0ang0lt0in.apps.googleusercontent.com";
	    private String oauthSecret = "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";
	    private String refreshToken = "1//0hnpETCo8GLEPCgYIARAAGBESNwF-L9IriogmDApaFjrXpi1-QrBEondmNRioFhb9X3vIxhGCWTYOxAtr12E3HUxBnJIpuQJxn4I"; 
	    private long tokenExpires = 1458168133864L;

		public static String getAccessToken() {
	        return accessToken;
	    }

	    public static void setAccessToken(String accessToken) {
	        accessToken = accessToken;
	    }

	    public void renewToken(){

	        if(System.currentTimeMillis() > tokenExpires) {

	            try
	            {
	                String request = "client_id="+ URLEncoder.encode(oauthClientId, "UTF-8")
	                        +"&client_secret="+URLEncoder.encode(oauthSecret, "UTF-8")
	                        +"&refresh_token="+URLEncoder.encode(refreshToken, "UTF-8")
	                        +"&grant_type=refresh_token";
	                HttpURLConnection conn = (HttpURLConnection) new URL(TOKEN_URL).openConnection();
	                conn.setDoOutput(true);
	                conn.setRequestMethod("POST");
	                PrintWriter out = new PrintWriter(conn.getOutputStream());
	                out.print(request);
	                out.flush();
	                out.close();
	                conn.connect();

	                try
	                {

	                    HashMap<String,Object> result;
	                    result = new ObjectMapper().readValue(conn.getInputStream(), new TypeReference<HashMap<String,Object>>() {});
	                    accessToken = (String) result.get("access_token");
	                    tokenExpires = System.currentTimeMillis()+(((Number)result.get("expires_in")).intValue()*1000);
	                }
	                catch (IOException e)
	                {
	                    String line;
	                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	                    while((line = in.readLine()) != null) {
	                        System.out.println(line);
	                    }
	                    System.out.flush();
	                }
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
}
