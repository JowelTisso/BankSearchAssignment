package com.jangphong.hem.banksearchassignment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String URL_DATA = "https://vast-shore-74260.herokuapp.com/banks?city=MUMBAI";
    private RecyclerView recyclerView;
    private List<ListItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*listItems = new ArrayList<>();

        for (int i=0;i<=10;i++)
        {
            ListItem listItem = new ListItem(
              "SBI"+(i+1),
              "Lorem ipsum",
              "SBIN00009945",
              "khanapara",
              "9945",
              "ghy",
              "kamrup",
              "assam"


            );
            listItems.add(listItem);
        }

        Radapter = new MyAdapter(listItems,this);
        recyclerView.setAdapter(Radapter);*/

        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Select City", "Mumbai", "Bangalore", "Chennai"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView txtView = (TextView) view;
                if (position == 0) {
                    txtView.setTextColor(Color.GRAY);
                } else {
                    txtView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        dropdown.setAdapter(adapter);


        listItems = new ArrayList<>();

        loadRecyclerViewData();




    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        progressDialog.dismiss();



                        try {
                            JSONArray jsonArray = new JSONArray(s);



                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject o = (JSONObject) jsonArray.get(i);
                                ListItem item = new ListItem(


                                        o.getString("bank_name"),
                                        o.getString("address"),
                                        o.getString("ifsc"),
                                        o.getString("branch"),
                                        o.getString("bank_id"),
                                        o.getString("city"),
                                        o.getString("district"),
                                        o.getString("state")

                                );
                                listItems.add(item);

                            }
                            RecyclerView.Adapter Radapter = new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(Radapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("errorcode", error.getMessage());
                    }
                }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}







