package com.descolab.moviecatalague.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.descolab.moviecatalague.model.TvShowResponse;
import com.descolab.moviecatalague.model.TvShowSourcesCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TvShowFragment extends Fragment implements TvShowSourcesCallBack {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewTvShow;
    private ArrayList<TvShow> tvShowArrayList = new ArrayList<TvShow>();
    private ListTvShowAdapter adapter;
    private String KEY_TVSHOW = "TvShow";


    public TvShowFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        progressDialog = new ProgressDialog(getActivity());
        getDataTv();
        adapter = new ListTvShowAdapter(tvShowArrayList);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewTvShow = view.findViewById(R.id.rv_tvshow);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewTvShow.setLayoutManager(layoutManager);
        recyclerViewTvShow.setHasFixedSize(true);


    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {

            tvShowArrayList = savedInstanceState.getParcelableArrayList(KEY_TVSHOW);
            adapter.refill(tvShowArrayList);
            progressDialog.dismiss();
        } else {

            getDataTv();
            progressDialog.dismiss();

        }
    }

    @Override
    public void onSuccess(TvShowResponse tvShowResponse) {
        tvShowArrayList = tvShowResponse.getResults();
        adapter.refill(tvShowArrayList);
    }

    @Override
    public void onFailed(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save the fragment's state here
        outState.putParcelableArrayList(KEY_TVSHOW, new ArrayList<>(tvShowArrayList));
        super.onSaveInstanceState(outState);

    }


    private void getDataTv() {
        progressDialog.setMessage(getString(R.string.dialog_loading));
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(getActivity());

        String url = getString(R.string.ip_default) + "tv?api_key=" + getString(R.string.api_key_themoviesdb_auth) + "&language=en-US";
        Log.d("Cek URL ", "URL GET Data TV: " + url);


        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Log.d("Cek", "onResponse: " + response);
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
                    adapter.notifyDataSetChanged();
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
