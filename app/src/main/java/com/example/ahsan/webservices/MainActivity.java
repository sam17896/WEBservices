package com.example.ahsan.webservices;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyFragment.Callback {
    List<Quotes> quotesList;
    List<MyTask> tasks;
    Boolean isTwoPane = false;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quotesList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        tasks = new ArrayList<>();
        if(findViewById(R.id.detail_container)!=null)
        {
            isTwoPane=true;
        }
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.GD:
                if(isOnline()) {
                    requestData("http://qoutes.fahadfarooq.com/");
                }
                else {
                    Toast.makeText(this,"Network isn't Available",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.dimen:
                ScreenDisplay sd = new ScreenDisplay(this);
                String msg = "Width: " + sd.getDpWidht()
                        + "\n" + "Heigth: " + sd.getDpHeight();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(msg).setTitle("Dimensions").show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void updateDisplay(){
        MyFragment frag = new MyFragment();
        getFragmentManager().beginTransaction().replace(R.id.container,frag).addToBackStack(null).commit();
    }
    public void clearDisplay()
    {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab:
                Intent i = new Intent(this,Upload.class);
                startActivity(i);
                break;
        }
    }
    public void requestData(String url)
    {
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(url);
        p.setParam("name", "Ahsan");
        MyTask task = new MyTask();
        task.execute(p);
    }
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info!=null&&info.isConnectedOrConnecting()) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOnline()) {
            requestData("http://qoutes.fahadfarooq.com/");
        }
        else {
            Toast.makeText(this,"Network isn't Available",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(Quotes quotes) {
        Bundle b = new Bundle();
        b.putString("name", quotes.getAuthorname());
        b.putString("cat", quotes.getCategory());
        b.putString("quote", quotes.getQuote());
        if (isTwoPane) {
            QuoteDetail frag = new QuoteDetail();
            frag.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.detail_container, frag).commit();
        } else {
            Intent i = new Intent(this, QuoteDetailActivity.class);
            i.putExtra("bundle", b);
            startActivityForResult(i, 1001);
        }
    }

    private class MyTask extends AsyncTask<RequestPackage,String,String> {
        @Override
        protected void onPreExecute() {
            clearDisplay();

            if (tasks.size() == 0) {
                pb.setVisibility(ProgressBar.VISIBLE);
            }

            tasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            quotesList = HttpManager.quoteList;
            tasks.remove(this);

            if( tasks.size() == 0) {
                pb.setVisibility(ProgressBar.INVISIBLE);
            }
            updateDisplay();
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }

}
