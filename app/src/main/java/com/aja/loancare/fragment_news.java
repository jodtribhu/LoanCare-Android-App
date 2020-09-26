package com.aja.loancare;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aja.loancare.model.NewsMOdel;
import com.aja.loancare.volley.MySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class fragment_news extends Fragment implements NewsRecyclerAdapter.onItemisCLick {
    ImageButton settings;
    ArrayList<NewsMOdel> newsList=new ArrayList<>();
    private RequestQueue mQueue;
    private NewsRecyclerAdapter recyclerAdapter2;
    RecyclerView ns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_news, container, false);
        settings=v.findViewById(R.id.settings_id);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingsActivity.class);
                startActivity(intent);

            }
        });



        mQueue= MySingleton.getInstance(getActivity()).getRequestQueue();

        String turl="https://bfsi.economictimes.indiatimes.com/rss/banking";
        StringRequest mRequest=new StringRequest(Request.Method.GET, turl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document= Jsoup.parse(response);
                Elements itemElements=document.select("item");
                Elements Linkss=document.select("link");
                for(int i=0;i<itemElements.size();i++)
                {
                    Element item=itemElements.get(i);
                    String titlefirst=item.child(0).text();
                    String title=titlefirst.replaceAll("CDATA", "");
                    title=title.replaceAll("[^0-9a-zA-Z:, ?]+", "");
                    String description=item.child(2).text();
                    String pubdate=item.child(4).text();

                    //Extracting newslink from image tag

                    String imageLink=item.select("img").toString();
                    String[] seperate1=imageLink.split("link=");
                    String[] seperate2=seperate1[1].split("src=");
                    String link =seperate2[0].replace("\"","");


                    NewsMOdel model=new NewsMOdel(title,link,description,pubdate);
                    newsList.add(model);
                }
                recyclerAdapter2.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(mRequest);
        ns= v.findViewById(R.id.recyclernews);
        recyclerAdapter2= new NewsRecyclerAdapter(getActivity(),newsList);
        ns.setAdapter(recyclerAdapter2);
        ns.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter2.onItemisCLickListener(this);

        return v;
    }


    @Override
    public void onClickListener(int position) {
        NewsMOdel newsMOdel=newsList.get(position);
        String url=newsMOdel.getLink();
        try {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "No application can handle this request." + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}