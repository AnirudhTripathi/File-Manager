package com.example.filemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

public class browse extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public browse() {
        // Required empty public constructor
    }


    public static browse newInstance(String path) {
        browse fragment = new browse();
        Bundle args = new Bundle();
        args.putString("path", path);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.Recycler_view);
        TextView noFilesText = rootView.findViewById(R.id.nofiles_textview);
    //    return inflater.inflate(R.layout.fragment_browse, container, false);
      //  RecyclerView recyclerView = findViewById(R.id.Recycler_view);
        // TextView noFilesText = findViewById(R.id.nofiles_textview);
        String path = Environment.getExternalStorageDirectory().getPath();
//        String path = getArguments().getString("path");

        File root = new File(path);
        File[] filesAndFolders = root.listFiles();

        if(filesAndFolders==null || filesAndFolders.length ==0){
            noFilesText.setVisibility(View.VISIBLE);
            return rootView;
        }

        noFilesText.setVisibility(View.INVISIBLE);


        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new MyAdapter(requireContext(), filesAndFolders));

        return rootView;
    }
}