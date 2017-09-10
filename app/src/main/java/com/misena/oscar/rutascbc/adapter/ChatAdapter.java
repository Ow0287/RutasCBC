package com.misena.oscar.rutascbc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.misena.oscar.rutascbc.modelo.MensajeChat;
import com.misena.oscar.rutascbc.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter{

    private List<MensajeChat> lista;
    private Context context;
    private String correo;

    public ChatAdapter(List<MensajeChat> lista, Context context, String correo) {
        this.lista = lista;
        this.context = context;
        this.correo = correo;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setLista(List<MensajeChat> lista) {
        this.lista = lista;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MensajeChat mensajeChat = lista.get(i);
        View fila =view;
        if (fila==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila= inflater.inflate(R.layout.plantilla_chat,viewGroup,false);

        }
        if (fila != null){

            TextView mensaje = fila.findViewById(R.id.txv_nombre_chat);
            TextView nombre  = fila.findViewById(R.id.txv_chat);
            ImageView imageView = fila.findViewById(R.id.img_chat);

            mensaje.setText(mensajeChat.getMensaje());
            nombre.setText(mensajeChat.getUsuario());

            if (mensajeChat.getUsuario().equals(correo)){

                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        }

        return fila;
    }
}
