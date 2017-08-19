package com.misena.oscar.rutascbc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.misena.oscar.rutascbc.R;
import com.misena.oscar.rutascbc.modelo.ModelGaleria;


import java.util.List;

/**
 * Created by user on 19/08/2017.
 */

public class AdapterGaleria extends BaseAdapter {
    List<ModelGaleria> galerias;
    Context context;
    @Override
    public int getCount() {
        return galerias.size();
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
        View fila =view;
        if (fila==null){
            LayoutInflater iflalo =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila= iflalo.inflate(R.layout.plantilla_galeria,viewGroup,false);
        }
        TextView tvLista=(TextView)fila.findViewById(R.id.txv_lista);
        tvLista.setText(galerias.get(i).getNombre());
        return fila;
    }
}
