package com.furazin.android.calorias;

import android.database.Cursor;

import com.furazin.android.calorias.DataBase.RegistroDbSchema;
import com.furazin.android.calorias.DataBase.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.furazin.android.calorias.FirstFragment.mDatabase;

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
        Cursor cursor = mDatabase.query (
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

    public boolean CheckDateRow(String date) {
        boolean exist = false;

        UserCursorWrapper cursor = queryUsers(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.equals(date))
                    exist = true;
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return exist;
    }

    public void EliminarRow(String date) {
//        UserCursorWrapper cursor = queryUsers(null, null);
//        try{
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                if (cursor.equals(date))
//                    cursor.
//                cursor.moveToNext();
//            }
//        } finally {
//            cursor.close();
//        }
//        cursor.

        mDatabase.delete(RegistroDbSchema.UserTable.NAME, RegistroDbSchema.UserTable.Cols.DATE + "=?", new String[]{String.valueOf(date)});
    }
}
