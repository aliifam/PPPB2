package com.aliif.utsp3b2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Intro2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Intro2 extends Fragment {

    public Intro2() {
        // Required empty public constructor
    }

    public static Intro2 newInstance() {
        return new Intro2();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro2, container, false);
    }
}