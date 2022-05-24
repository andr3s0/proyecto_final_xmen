package com.example.x_men;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ListAdapter extends BaseAdapter {

    private Context myContext;
    private String[] nombre_, alias_, afiliacion_, img_;
    private LayoutInflater inflater;

    public ListAdapter(Context myContext, String[] name, String[] alias, String[] affiliation, String[] img) {
        this.myContext = myContext;
        this.nombre_ = name;
        this.alias_ = alias;
        this.afiliacion_ = affiliation;
        this.img_ = img;
        inflater = LayoutInflater.from(myContext);
    }

    @Override
    public int getCount() {
        return nombre_.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_element, null);

        TextView name = view.findViewById(R.id.nombre_personaje);
        TextView alias = view.findViewById(R.id.alias);
        TextView afiliacion = view.findViewById(R.id.afiliacion_personaje);
        TextView powers = view.findViewById(R.id.powers);
        ImageView img = view.findViewById(R.id.img_personaje);

        name.setText(nombre_[i]);
        alias.setText(alias_[i]);
        afiliacion.setText(afiliacion_[i]);
        Picasso.get()
                .load(img_[i])
                .error(R.mipmap.ic_launcher)
                .into(img);

        return view;
    }
}
