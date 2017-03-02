package com.example.ahsan.webservices;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class QuoteDetail extends Fragment {

    Quotes quote;
    public QuoteDetail(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if(b!=null && b.containsKey("name")) {
            quote = new Quotes(b);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_detail, container, false);


        TextView tv1,tv2,tv3;
        tv1 = (TextView) view.findViewById(R.id.dQ);
        tv2 = (TextView) view.findViewById(R.id.dA);
        tv3 = (TextView) view.findViewById(R.id.dC);

        tv1.setText(quote.getQuote());
        tv2.setText(quote.getAuthorname());
        tv3.setText(quote.getCategory());
        return view;
    }
}
