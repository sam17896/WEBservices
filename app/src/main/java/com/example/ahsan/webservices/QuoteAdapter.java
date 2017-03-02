package com.example.ahsan.webservices;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class QuoteAdapter extends ArrayAdapter<Quotes> {
    private Context con;
    private List<Quotes> quotesList;
    public QuoteAdapter(Context context, int resource, List<Quotes> objects) {
        super(context, resource, objects);
        con = context;
        quotesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.quote_item, parent, false);

        Quotes quotes = quotesList.get(position);
        TextView tvQ = (TextView) view.findViewById(R.id.tvQ);
        tvQ.setText(quotes.getQuote());
        return view;
    }
}

        // Image View
        /*
        if(quotes.getBitmap()!=null)
        {
            ImageView im = (ImageView) view.findViewById(R.id.im);
            im.setImageBitmap(quotes.getBitmap());
        }
        else
        {
            Container c = new Container();
            c.quote= quotes;
            c.view =view;
            ImageLoad img = new ImageLoad();
            img.execute(c);
        }

        return view;
    public class Container{
        public Quotes quote;
        public View view;
        public Bitmap bitmap;
    }
    public class ImageLoad extends AsyncTask<Container,Void,Container>{

        @Override
        protected Container doInBackground(Container... params) {
            Container container = params[0];
            try{
                String imageUrl = "image url";
                InputStream is = (InputStream) new URL(imageUrl).getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                container.bitmap = bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return container;
        }

        @Override
        protected void onPostExecute(Container container) {
            ImageView im = (ImageView) container.view.findViewById(R.id.im);
            im.setImageBitmap(container.quote.getBitmap());
            container.quote.setBitmap(container.bitmap);
        }
    }

}
*/
