package com.example.ahsan.webservices;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

public class QuoteDetailActivity extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

//        getActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle b = getIntent().getBundleExtra("bundle");
        QuoteDetail quoteDetail = new QuoteDetail();
        quoteDetail.setArguments(b);

        getFragmentManager().beginTransaction().replace(R.id.detail_container,quoteDetail).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
