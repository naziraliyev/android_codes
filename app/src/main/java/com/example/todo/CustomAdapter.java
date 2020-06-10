package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private  List<Tasks> list;
    private Context context;
    DataBase dataBase;

    public CustomAdapter(List<Tasks> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom,null);
        }

        TextView listItemtext = view.findViewById(R.id.textview1);
        dataBase = new DataBase(context);
        listItemtext.setText(list.get(position).getTasks());

        Button Delete = view.findViewById(R.id.delete);
        Button Edit = view.findViewById(R.id.edit);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://api.todoist.com/rest/v1/projects/1234", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);

                            for (int i  = 0; i < jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String project = jsonObject.getString("project");
                                dataBase.DeleteTable(list.get(position).getId());
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }}
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("Authorization", "Bearer " + "Zs1ahJjWROqjBMIJGCAmsToOXOEP-wa9x3HiuBEyr6ymUYoyAyhIHeXkS9HE9HfFXcAO");

                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

            }



        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Edit_task_Data = MainActivity.task_edit.getText().toString();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://api.todoist.com/rest/v1/projects/1234", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);

                            for (int i  = 0; i < jsonArray.length(); i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String project = jsonObject.getString("project");
                                dataBase.UpdateTable(list.get(position).getId(),Edit_task_Data);
                                list.get(position).setTasks(Edit_task_Data);
                                notifyDataSetChanged();
                                 }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }}
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("Authorization", "Bearer " + "Zs1ahJjWROqjBMIJGCAmsToOXOEP-wa9x3HiuBEyr6ymUYoyAyhIHeXkS9HE9HfFXcAO");


                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);

                }

            private int getCacheDir() {
                return 0;
            }
        });

        return view;
    }
}
