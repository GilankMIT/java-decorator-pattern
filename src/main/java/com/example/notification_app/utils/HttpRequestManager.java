package com.example.notification_app.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public class HttpRequestManager {

    private static HttpRequestManager manager = null;

    private HttpRequestManager() {}

    public static HttpRequestManager getInstance() {
        if (manager == null)
            manager = new HttpRequestManager();
        return manager;
    }

    public String postUrlConnection(String requestUrl, JSONObject jsonParams) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }


    public String postUrlMultipartFormData(String requestUrl, Map<String, String> data) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";

        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            //build URL Parameter
            StringBuilder paramBuilder = new StringBuilder();
            for (Map.Entry<String, String> pair : data.entrySet()) {
                paramBuilder.append(pair.getKey()).append("=").append(pair.getValue());
                paramBuilder.append("&");
            }
            String postParameter = paramBuilder.toString();
            byte[] postData       = postParameter.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF=8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches( false );
            con.setDoOutput(true);
            con.getOutputStream().write(postData);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;

    }

    public String postUrlConnectionEncoded(String requestUrl, String jsonParams) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF=8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams);
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String postUrlWithCustomHeader(String requestUrl, String jsonParams, Map<String, String> header) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF=8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());

            for(String key : header.keySet() ){
                con.setRequestProperty(key, header.get(key));
            }

            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams);
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String postUrlWithCustomHeaderJSON(String requestUrl, String jsonParams, Map<String, String> header) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(150000);
            con.setConnectTimeout(150000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "*/*");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());

            for(String key : header.keySet() ){
                con.setRequestProperty(key, header.get(key));
            }

            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams);
            os.flush();
            os.close();

            System.out.println(requestUrl);

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode >= 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
                System.out.println("Getting error http code from request " + requestUrl + rev);
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String postUrlArrayAuthorization(String requestUrl, JSONArray jsonParams, String userToken) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("ACCEPT", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setRequestProperty("Authorization", "Bearer " + userToken);
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if ((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_CREATED)) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String postUrlConnectionAuthorization(String requestUrl, JSONObject jsonParams, String userToken) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("ACCEPT", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setRequestProperty("Authorization", "Bearer " + userToken);
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if ((responseCode == HttpURLConnection.HTTP_OK) || (responseCode == HttpURLConnection.HTTP_CREATED)) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    private void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws java.security.cert.CertificateException {
            }
        } };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
            return hv.verify("", session);
        }
    };

    public String putUrlConnectionAuthorization(String requestUrl, JSONObject jsonParams, String userToken) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setRequestProperty("Authorization", "Bearer " + userToken);
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 201) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String deleteUrlConnectionAuthorization(String requestUrl, JSONObject jsonParams, String userToken) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
            con.setRequestProperty("deviceType", "android");
            con.setRequestProperty("Authorization", "Bearer " + userToken);
            con.setDoInput(true);
            con.setDoOutput(true);
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                rev = "" + responseCode;
            } else if (responseCode == 400) {
                rev = "" + responseCode;
            } else {
                rev = "" + responseCode;
            }
        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }
        return rev;
    }

    public String getUrlConnectionAuthorization(String requestUrl, String params, String userToken) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl + "?" + params);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestProperty("Authorization", "Bearer " + userToken);
            InputStream is = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }

        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String getUrlConnectionCustomHeader(String requestUrl, String params, Map<String, String> headers) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl + "?" + params);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);

            for(Map.Entry<String, String> entry : headers.entrySet()){
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            InputStream is = con.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                rev = null;
            }

        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }

    public String getUrlConnection(String requestUrl, String params) {
        URL url = null;
        HttpURLConnection con = null;
        String rev = "";
        try {
            url = new URL(requestUrl + "?" + params);
            if (url.getProtocol().toLowerCase().equals("https")) {
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else if (responseCode == 400) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            } else {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                while ((line = br.readLine()) != null) {
                    rev += line;
                }
                br.close();
            }

        } catch (MalformedURLException e) {
            rev = null;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            rev = null;
        }

        return rev;
    }
}
