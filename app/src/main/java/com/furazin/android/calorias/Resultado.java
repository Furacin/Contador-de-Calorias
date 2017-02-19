package com.furazin.android.calorias;

/**
 * Created by manza on 19/02/2017.
 */

public class Resultado {
    private String fecha;
    private String numero;

    public Resultado(String fecha, String numero) {
        this.fecha = fecha;
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNumero() {
        return numero;
    }
}
