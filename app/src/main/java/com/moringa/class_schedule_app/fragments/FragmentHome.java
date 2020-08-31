package com.moringa.class_schedule_app.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moringa.class_schedule_app.R;
import com.moringa.class_schedule_app.fireModel.SetData;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_home,container,false);

        ListView mlist=root.findViewById(R.id.mylist);
        List<SetData> setData;
        setData=new ArrayList<>();
        setData.add(new SetData("meeting","james","There will be a meeting at 10.45 today for all mc 40 students"));
        ListAdapter listAdapter=new ListAdapter(getContext(),R.layout.list_item,setData);
        mlist.setAdapter(listAdapter);
        return root;
    }
}
