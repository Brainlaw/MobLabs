package com.example.lab2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    final static String textViewTextkey = "TextView";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Button action_open = (Button) view.findViewById(R.id.open_button);
        action_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StorageActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(item);

    }

}