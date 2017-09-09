package com.misena.oscar.rutascbc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.misena.oscar.rutascbc.MensajeChat;
import com.misena.oscar.rutascbc.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter{

    private List<MensajeChat> lista;
    private Context context;
    private MensajeChat mensajeChat;

    public ChatAdapter(MensajeChat mensajeChat1, Context context) {
        this.mensajeChat = mensajeChat1;
        this.context = context;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View fila =view;
        if (fila==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fila= inflater.inflate(R.layout.plantilla_chat,viewGroup,false);
        }

        TextView mensaje = view.findViewById(R.id.txv_nombre_chat);
        TextView nombre  = view.findViewById(R.id.txv_chat);

        mensaje.setText(mensajeChat.getMensaje());
        nombre.setText(mensajeChat.getUsuario());

        return null;
    }
}
