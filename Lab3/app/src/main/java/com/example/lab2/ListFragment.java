package com.example.lab2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ListFragment extends Fragment {

    String choosed_option;
    private OnFragmentInteractionListener mListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        spinner(view);
        Button action_ok = (Button) view.findViewById(R.id.ok_button);
        action_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        Button action_cancel = (Button) view.findViewById(R.id.cancel_button);
        action_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosed_option = "Nothing was selected";
                updateDetail();
            }
        });
        return view;
    }

    interface OnFragmentInteractionListener {

        void onFragmentInteraction(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }
    public void spinner(View v){String[] choices = getResources().getStringArray(R.array.choices);
        Spinner spinner = (Spinner) v.findViewById(R.id.choices);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choosed_option = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choosed_option = "";
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }
    public void updateDetail() {
        mListener.onFragmentInteraction(choosed_option);
        if((!(choosed_option.equals("")))&&(!(choosed_option.equals("Nothing was selected")))){
            String option;
            option = "3";
            SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("app.db", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS choices (name TEXT)");
            db.execSQL("INSERT INTO choices VALUES ('" + choosed_option + "');");
            db.close();
        }

    }
}