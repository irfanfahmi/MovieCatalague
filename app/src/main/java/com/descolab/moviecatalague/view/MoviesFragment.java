package com.descolab.moviecatalague.view;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
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
import com.descolab.moviecatalague.adapter.ListMoviesAdapter;
import com.descolab.moviecatalague.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
    private ListMoviesAdapter adapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewMovies;
    private SearchView searchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    String textSearch = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerViewMovies = view.findViewById(R.id.rv_movies);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewMovies.setLayoutManager(layoutManager);
        recyclerViewMovies.setHasFixedSize(true);
        getData();
        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh_items);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.primaryColor);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Berhenti berputar/refreshing
                        mSwipeRefreshLayout.setRefreshing(false);
                        getDataMovies();

                    }
                }, 2000);
            }
        });

        return view;
    }
    private void getData(){


        progressDialog.setMessage(getString(R.string.dialog_loading));
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.ip_default)+"movie?api_key="+getString(R.string.api_key_themoviesdb_auth)+"&language=en-US";
        Log.d("Cek URL ", "URL GET MOVIE: "+url);


        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override public void onResponse(String response) {
                Log.d("Cek", "onResponse: "+response);
                progressDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("results");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);

                        Movie data = new Movie(
                                json.getString("id"),
                                json.getString("title"),
                                json.getString("popularity"),
                                json.getString("vote_count"),
                                json.getString("video"),
                                json.getString("poster_path"),
                                json.getString("adult"),
                                json.getString("backdrop_path"),
                                json.getString("original_language"),
                                json.getString("original_title"),
                                json.getString("vote_average"),
                                json.getString("overview"),
                                json.getString("release_date")
                        );

                        movieArrayList.add(data);


                    }
                    adapter = new ListMoviesAdapter(getActivity(), movieArrayList);

                    recyclerViewMovies.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
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



    private void getDataMovies(){
       mSwipeRefreshLayout.setRefreshing(true);

       progressDialog.setMessage(getString(R.string.dialog_loading));
       progressDialog.show();

       requestQueue = Volley.newRequestQueue(getActivity());

       String url = getString(R.string.ip_default)+"movie?api_key="+getString(R.string.api_key_themoviesdb_auth)+"&language=en-US";
       Log.d("Cek URL ", "URL GET MOVIE: "+url);


       stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           @Override public void onResponse(String response) {
               Log.d("Cek", "onResponse: "+response);
               progressDialog.dismiss();

               try {
                   JSONObject obj = new JSONObject(response);
                   JSONArray jsonArray = obj.getJSONArray("results");
                   for (int a = 0; a < jsonArray.length(); a ++){
                       JSONObject json = jsonArray.getJSONObject(a);

                       Movie data = new Movie(
                               json.getString("id"),
                               json.getString("title"),
                               json.getString("popularity"),
                               json.getString("vote_count"),
                               json.getString("video"),
                               json.getString("poster_path"),
                               json.getString("adult"),
                               json.getString("backdrop_path"),
                               json.getString("original_language"),
                               json.getString("original_title"),
                               json.getString("vote_average"),
                               json.getString("overview"),
                               json.getString("release_date")
                       );

                       movieArrayList.add(data);


                   }
                   adapter = new ListMoviesAdapter(getActivity(), movieArrayList);

                   recyclerViewMovies.setAdapter(adapter);


               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override public void onErrorResponse(VolleyError error) {
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
       mSwipeRefreshLayout.setRefreshing(false);
   }


    @Override
    public void onRefresh() {
        getDataMovies();
    }


}
