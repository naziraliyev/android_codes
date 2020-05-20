package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private  List<Topshiriqlar> list = new ArrayList<>();
    private Context context;
    DataBase dataBase;

    public CustomAdapter(List<Topshiriqlar> list, Context context){
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
        TextView listItemtext = view.findViewById(R.id.TextView1);
        dataBase = new DataBase(context);
        listItemtext.setText(list.get(position).getTopshiriq());

        Button Delete = view.findViewById(R.id.delete);
        Button Edit = view.findViewById(R.id.edit);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.ochirishjadval(list.get(position).getId());
                list.remove(position);
                notifyDataSetChanged();

            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String top = MainActivity.topshiriq.getText().toString();
                if( top.equals("")){

                }else {
                dataBase.ozgarrishjadval(list.get(position).getId(),top);
                list.get(position).setTopshiriq(top);
                notifyDataSetChanged();
            }}
        });

        return view;
    }
}
