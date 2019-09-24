package com.descolab.moviecatalague.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.descolab.moviecatalague.R;
import com.descolab.moviecatalague.adapter.ListTvShowAdapter;
import com.descolab.moviecatalague.model.TvShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TvShowFragment extends Fragment {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewTvShow;
    private ArrayList<TvShow> tvShowArrayList = new ArrayList<TvShow>();
    private ListTvShowAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        getDataTv();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerViewTvShow = view.findViewById(R.id.rv_tvmovies);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewTvShow.setLayoutManager(layoutManager);
        recyclerViewTvShow.setHasFixedSize(true);


        return view;
    }

    private void getDataTv() {
        progressDialog.setMessage(getString(R.string.dialog_loading));
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.ip_default) + "tv?api_key=" + getString(R.string.api_key_themoviesdb_auth) + "&language=en-US";
        Log.d("Cek URL ", "URL GET TV: " + url);


        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Cek", "onResponse: " + response);
                progressDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("results");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);

                        TvShow data = new TvShow(
                                json.getString("id"),
                                json.getString("name"),
                                json.getString("popularity"),
                                json.getString("vote_count"),
                                json.getString("poster_path"),
                                json.getString("backdrop_path"),
                                json.getString("original_language"),
                                json.getString("vote_average"),
                                json.getString("overview"),
                                json.getString("first_air_date")
                        );

                        tvShowArrayList.add(data);


                    }
                    adapter = new ListTvShowAdapter(getActivity(), tvShowArrayList);

                    recyclerViewTvShow.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), getString(R.string.text_error_NoConnectionError), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getActivity(), getString(R.string.text_error_AuthFailureError), Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getActivity(), getString(R.string.text_error_ServerError), Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getActivity(), getString(R.string.text_error_NetworkError), Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getActivity(), getString(R.string.text_error_ParseError), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Adding request to request queue
        requestQueue.add(stringRequest);

    }


}
