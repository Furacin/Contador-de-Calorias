package com.furazin.android.calorias;

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

/**
 * Created by manza on 17/02/2017.
 */

public class FirstFragment extends Fragment {

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

        return Math.ceil(calorias);
    }

}
