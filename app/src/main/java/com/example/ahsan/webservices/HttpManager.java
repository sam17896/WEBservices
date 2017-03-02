package com.example.ahsan.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class HttpManager {
    public static List<Quotes> quoteList = null;
    public List<Quotes> getQuoteList(){
        return quoteList;
    }
    public static String putData(RequestPackage p) throws UnsupportedEncodingException {
        BufferedReader reader = null;
        String uri = p.getUri();
        if (p.getMethod().equals("GET")) {
            uri += "?" + p.getEncodedParams();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(p.getMethod());

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }
    public static String getData(RequestPackage p)
    {
        String uri = p.getUri();
        quoteList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while((line = reader.readLine()) !=null)
            {
                if(line.contains("<h1>")&&line.contains("</h1>")) {
                    Quotes quote = new Quotes();
                    String aut="",cat="",quo="";
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        quo = quo + line.charAt(i);
                    }
                    line = reader.readLine();
                    line = reader.readLine();
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        aut = aut + line.charAt(i);
                    }
                    line = reader.readLine();
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        cat = cat + line.charAt(i);
                    }
                    sb.append(quo+"\n");
                    sb.append(aut + "\n");
                    sb.append(cat + "\n"+"\n");
                    quote.setAuthorname(aut);
                    quote.setCategory(cat);
                    quote.setQuote(quo);
                    quoteList.add(quote);
                }else if(line.contains("<h1>")&&!line.contains("</h1>"))
                {
                    do {
                        line = line +"\n"+ reader.readLine();
                    }while(!line.contains("</h1>"));
                    Quotes quote = new Quotes();
                    String aut="",cat="",quo="";
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        quo = quo + line.charAt(i);
                    }
                    line = reader.readLine();
                    line = reader.readLine();
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        aut = aut + line.charAt(i);
                    }
                    line = reader.readLine();
                    for(int i = line.indexOf('>')+1;i<line.indexOf('<',line.indexOf('>'));i++)
                    {
                        cat = cat + line.charAt(i);
                    }
                    sb.append(quo+"\n");
                    sb.append(aut + "\n");
                    sb.append(cat + "\n"+ "\n");
                    quote.setAuthorname(aut);
                    quote.setCategory(cat);
                    quote.setQuote(quo);
                    quoteList.add(quote);
                }
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(reader!=null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
