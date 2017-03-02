package com.example.ahsan.webservices;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class MyFragment extends ListFragment {

    List<Quotes> quotesList = HttpManager.quoteList;
    public Callback actvity;
    public MyFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QuoteAdapter adapter = new QuoteAdapter(getActivity(), R.layout.quote_item, quotesList);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Quotes quotes = quotesList.get(position);
        actvity.onItemSelected(quotes);

    }

    public interface Callback{
        public void onItemSelected(Quotes quotes);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.actvity = (Callback) activity;
    }
}
