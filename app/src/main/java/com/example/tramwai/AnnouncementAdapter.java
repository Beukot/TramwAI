package com.example.tramwai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AnnouncementAdapter extends ArrayAdapter<Announcement> {

    public AnnouncementAdapter(Context context, ArrayList<Announcement> announcements) {
        super(context, 0, announcements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Announcement announcement = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);

        titleTextView.setText(announcement.getTitle());
        descriptionTextView.setText(announcement.getDescription());

        return convertView;
    }
}