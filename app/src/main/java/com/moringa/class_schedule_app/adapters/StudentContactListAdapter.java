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

import com.moringa.class_schedule_app.R;

public class StudentContactListAdapter extends ArrayAdapter<StudentModel> {

    private ArrayList<StudentModel> students;
    Context mContext;

    public StudentContactListAdapter(ArrayList<StudentModel> students, Context context) {
        super(context, R.layout.contact_list_item);
        this.students = students;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        StudentModel student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        final View result;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.contact_list_item, parent, false);
            viewHolder.username = (TextView) view.findViewById(R.id.contactUsername);
            viewHolder.profileImage = (ImageView) view.findViewById(R.id.userProfileContact);

            result = view;
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            result = view;
        }

        viewHolder.username.setText(student.getName());
        return view;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView username;
        ImageView profileImage;
    }

}
