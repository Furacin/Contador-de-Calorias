package com.furazin.android.calorias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.furazin.android.calorias.DataBase.UserBaseHelper;
import com.furazin.android.calorias.DataBase.UserCursorWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.furazin.android.calorias.DataBase.RegistroDbSchema.UserTable;

/**
 * Created by manza on 17/02/2017.
 */

public class FirstFragment extends Fragment {

    // Variables para la BD
//    private List<Resultado> users;
    private Context context;
    private SQLiteDatabase mDatabase;

    public static final String ARG_PAGE = "ARG_PAGE";
    Button boton_calcular;
    TextView caloriasTextView;
    private int mPage;
    Spinner spinner;
    EditText editTextPeso;
    EditText editTextTiempo;

    public static FirstFragment newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_page, container, false);

        caloriasTextView = (TextView) view.findViewById(R.id.TextViewCalorias);

        boton_calcular = (Button) view.findViewById(R.id.botoncalcular);
        boton_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                double calorias = CalcularCalorias(v);
                caloriasTextView.setText("" + calorias + " cal");
            }
         });

        spinner = (Spinner) view.findViewById(R.id.spNumbers);
        editTextPeso = (EditText) view.findViewById(R.id.editTextPeso);
        editTextTiempo = (EditText) view.findViewById(R.id.editTextTiempo);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        // Fragment
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPage = getArguments().getInt(ARG_PAGE);

        // BD
        context = getActivity();
        mDatabase = new UserBaseHelper(context).getWritableDatabase();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu_main,menu);
    }

    double CalcularCalorias(View view) {

//        int fc = Integer.parseInt(SpinnerValue);
        String SpinnerValue = spinner.getSelectedItem().toString();
        int fc = Integer.parseInt(SpinnerValue);

        String EditTextValue = editTextPeso.getText().toString();
        int peso = Integer.parseInt(EditTextValue);

        String textTiempo = editTextTiempo.getText().toString();
        double tiempo = Integer.parseInt(textTiempo);

        double MET = 0;

        switch (fc) {
            case 134:
                MET = 6.2;
                break;
            case 141:
                MET = 7.3;
                break;
            case 148:
                MET = 8.3;
                break;
            case 155:
                MET = 9.4;
                break;
            case 162:
                MET = 10.4;
                break;
            case 169:
                MET = 11.4;
                break;
        }

        double calorias = tiempo * (MET * 3.5 * peso) / 200;
        calorias = Math.ceil(calorias);
        // Insertamos en BD
        ContentValues values = getContentValues(calorias);
        mDatabase.insert(UserTable.NAME, null, values);
        getResultados();

        return calorias;
    }

    // BD
    private static ContentValues getContentValues(double resultado) {
        ContentValues values = new ContentValues();

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        values.put(UserTable.Cols.VALOR, resultado);
        values.put(UserTable.Cols.DATE,date);

        return values;
    }

    private UserCursorWrapper queryUsers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query (
                UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new UserCursorWrapper(cursor);
    }

    public void getResultados() {

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

//        for (Resultado res: resultados) {
//            System.out.println(res.getNumero() + " - " + res.getFecha() );
//        }
    }

}
