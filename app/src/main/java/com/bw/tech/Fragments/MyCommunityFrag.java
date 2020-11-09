package com.bw.tech.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.tech.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCommunityFrag extends Fragment {


    public MyCommunityFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_community, container, false);
    }

}
