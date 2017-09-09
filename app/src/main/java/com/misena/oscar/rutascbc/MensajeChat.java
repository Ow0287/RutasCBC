package com.misena.oscar.rutascbc;

public class MensajeChat {

    private String mensaje;
    private String usuario;

    public MensajeChat(String mensaje, String usuario) {
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
