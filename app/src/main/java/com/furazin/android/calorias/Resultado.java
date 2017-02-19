package com.furazin.android.calorias;

import java.util.Date;

/**
 * Created by manza on 19/02/2017.
 */

public class Resultado {
    private Date fecha;
    private double numero;

    public Resultado(Date fecha, double numero) {
        this.fecha = fecha;
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getNumero() {
        return numero;
    }
}
