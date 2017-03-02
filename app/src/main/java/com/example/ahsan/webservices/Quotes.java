package com.example.ahsan.webservices;

import android.graphics.Bitmap;
import android.os.Bundle;

public class Quotes {

    private String authorname;
    private String category;
    private String quote;
    private Bitmap Bitmap;

    public Quotes(){

    }

    public Quotes(Bundle b){
        this.authorname = b.getString("name","N/A");
        this.category = b.getString("cat","N/A");
        this.quote = b.getString("quote","N/A");
    }

    public void setCategory(String name){
        category = name;
    }

    public void setQuote(String name){
        quote = name;
    }

    public void setAuthorname(String name){
        authorname = name;
    }

    public String getAuthorname(){
        return authorname;
    }

    public String getCategory() {
        return category;
    }

    public String getQuote() {
        return quote;
    }

    public Bitmap getBitmap() { return Bitmap; }

    public void setBitmap(Bitmap bitmap) { Bitmap = bitmap; }
}
