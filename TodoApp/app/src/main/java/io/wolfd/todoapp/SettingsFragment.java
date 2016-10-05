package io.wolfd.todoapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        /*
        Using Butterknife's @OnCLick annotation with an array of view ids, you could probably
        save a lot of code duplication here. It allows you to run the same block of code for
        all the views, and in this case, you could include a switch-case (or something similar)
        to achieve the same functionality with less repetition.
         */

        // basic settings here.
        view.findViewById(R.id.button_background_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the parent activity and call the public setBackgroundColor method
                final FragmentActivity activity = getActivity();

                ((TodoActivity) activity).setBackgroundColor(Color.BLUE);
            }
        });

        view.findViewById(R.id.button_background_white).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentActivity activity = getActivity();

                ((TodoActivity) activity).setBackgroundColor(Color.WHITE);
            }
        });

        view.findViewById(R.id.button_background_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentActivity activity = getActivity();

                ((TodoActivity) activity).setBackgroundColor(Color.RED);
            }
        });

        return view;
    }
}
