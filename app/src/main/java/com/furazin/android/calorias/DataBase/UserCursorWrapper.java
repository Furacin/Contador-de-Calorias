package com.furazin.android.calorias.DataBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.furazin.android.calorias.Resultado;

import static com.furazin.android.calorias.DataBase.RegistroDbSchema.*;

/**
 * Created by manza on 19/02/2017.
 */

public class UserCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Resultado getResultado() {
        String valor = getString(getColumnIndex(UserTable.Cols.VALOR));
        String fecha = getString(getColumnIndex(UserTable.Cols.DATE));

        Resultado resultado = new Resultado(fecha, valor);

        return resultado;
    }
}
