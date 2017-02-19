package com.furazin.android.calorias;

import android.database.Cursor;

import com.furazin.android.calorias.DataBase.RegistroDbSchema;
import com.furazin.android.calorias.DataBase.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manza on 19/02/2017.
 */

public class Resultado {
    private String fecha;
    private String numero;

    public Resultado() {}

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

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = FirstFragment.mDatabase.query (
                RegistroDbSchema.UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new UserCursorWrapper(cursor);
    }

    public List<Resultado> getResultadosBD() {

        List<Resultado> resultados = new ArrayList<>();

        UserCursorWrapper cursor = queryUsers(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                resultados.add(cursor.getResultado());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return resultados;

//        for (Resultado res: resultados) {
//            System.out.println(res.getNumero() + " - " + res.getFecha() );
//        }
    }
}
