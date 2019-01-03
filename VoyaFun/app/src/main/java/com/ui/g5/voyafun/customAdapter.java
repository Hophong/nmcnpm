package com.ui.g5.voyafun;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class customAdapter extends ArrayAdapter {
    private Context context;
    int layout;
    private List<Information> informs;

    public customAdapter(@NonNull Context context, int layoutToBeInflated, List<Information> informs ) {
        super(context, layoutToBeInflated,informs);
        this.context = context;
        this.layout = layoutToBeInflated;
        this.informs = informs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_row,null);

        ImageView img = view.findViewById(R.id.image);
        TextView txtTitle  = view.findViewById(R.id.txtTitle);

        Information content = informs.get(position);
        txtTitle.setText(content.getTitle());
        img.setImageResource(content.getImage());

        return view;
    }


}
