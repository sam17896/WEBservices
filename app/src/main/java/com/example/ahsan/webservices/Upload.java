package com.example.ahsan.webservices;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class Upload extends Activity implements View.OnClickListener {
    EditText ed1,ed2,ed3;
    Button m1;
    RequestPackage p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        ed1 = (EditText) findViewById(R.id.ETA);
        ed2 = (EditText) findViewById(R.id.ETC);
        ed3 = (EditText) findViewById(R.id.ETQ);
        p = new RequestPackage();
        m1 = (Button) findViewById(R.id.button);
        m1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
            {
                RequestPackage p = new RequestPackage();
                p.setMethod("GET");
                p.setUri("http://qoutes.fahadfarooq.com/upload1.php");
                p.setParam("name", ed1.getText().toString());
                p.setParam("cat", ed2.getText().toString());
                p.setParam("qoute", ed3.getText().toString());
                Task task = new Task();
                task.execute(p);
                break;
            }
        }
    }

    private class Task extends AsyncTask<RequestPackage,String,String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String s=null;
            try {
                 s = HttpManager.putData(params[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            String msg = "";
            for (int i=0;i<s.length();i++)
            {
                if(s.charAt(i)=='<')
                {
                    msg = msg + "\n";
                    i=i+3;
                }
                else {
                    msg = msg + s.charAt(i);
                }
            }
            s=msg;
            if(ed1.getText().toString().equals(""))
            {
                s = s + "\n"+"Author name is missing";
            }
            if(ed2.getText().toString().equals(""))
            {
                s = s+ "\n"+"Category is missing";
            }
            if(ed3.getText().toString().equals(""))
            {
                s = s+ "\n"+"Quote is missing";
            }
            Toast.makeText(Upload.this,s,Toast.LENGTH_LONG).show();
        }
    }
}
