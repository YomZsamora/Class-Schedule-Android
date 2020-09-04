package com.moringa.class_schedule_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringa.class_schedule_app.models.StudentModel;

import java.util.ArrayList;
import java.util.List;

import com.moringa.class_schedule_app.R;

public class StudentContactListAdapter extends ArrayAdapter<StudentModel> {

    private List<StudentModel> students;
    Context mContext;

    public StudentContactListAdapter(List<StudentModel> students, Context context) {
        super(context, R.layout.contact_list_item, students);
        this.students = students;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_list_item, parent, false);
        TextView username = (TextView) rowView.findViewById(R.id.contactUsername);
        ImageView profilePic = (ImageView) rowView.findViewById(R.id.userProfileContact);
        profilePic.setImageResource(R.drawable.profile_pic);
        username.setText(students.get(position).getName());
        return rowView;
    }


}
