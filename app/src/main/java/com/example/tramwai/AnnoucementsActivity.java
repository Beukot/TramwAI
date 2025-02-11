// AnnouncementsActivity.java
package com.example.tramwai;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;

public class AnnoucementsActivity extends AppCompatActivity {
    private ListView announcementsList;
    private ArrayList<Announcement> announcements;
    private Button addAnnouncementButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoucements);

        announcementsList = findViewById(R.id.announcementsList);
        addAnnouncementButton = findViewById(R.id.addAnnouncementButton);

        // Initialize sample data
        announcements = new ArrayList<>();
        announcements.add(new Announcement("George Droyd", "Nowy projekt rzadu - George D...", "A"));
        announcements.add(new Announcement("Nowa Piekarnia", "Pani Basia na ul. Basi Piekarnic...", "A"));
        announcements.add(new Announcement("Hala Targowa", "Hala Targowa zostala zamkniet...", "A"));
        announcements.add(new Announcement("Most Siennicki", "Z powodu nieznanych problem...", "A"));
        announcements.add(new Announcement("Kup reklamę", "Gdańsk - Miasto które zarabia...", "A"));

        AnnouncementAdapter adapter = new AnnouncementAdapter(this, announcements);
        announcementsList.setAdapter(adapter);

        // Handle item clicks
        announcementsList.setOnItemClickListener((parent, view, position, id) -> {
            // TODO: Launch edit activity
            // Intent intent = new Intent(AnnouncementsActivity.this, EditAnnouncementActivity.class);
            // intent.putExtra("announcement", announcements.get(position));
            // startActivity(intent);
        });

        addAnnouncementButton.setOnClickListener(v -> {
            // TODO: Handle adding new announcement
        });
    }
}

// Announcement.java
class Announcement {
    private String title;
    private String description;
    private String type;

    public Announcement(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
}

// AnnouncementAdapter.java
class AnnouncementAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Announcement> announcements;
    private LayoutInflater inflater;

    public AnnouncementAdapter(Context context, ArrayList<Announcement> announcements) {
        this.context = context;
        this.announcements = announcements;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return announcements.size();
    }

    @Override
    public Object getItem(int position) {
        return announcements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.announcement_item, parent, false);
            holder = new ViewHolder();
            holder.starIcon = convertView.findViewById(R.id.starIcon);
            holder.title = convertView.findViewById(R.id.announcementTitle);
            holder.description = convertView.findViewById(R.id.announcementDescription);
            holder.typeLabel = convertView.findViewById(R.id.typeLabel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Announcement announcement = announcements.get(position);
        holder.title.setText(announcement.getTitle());
        holder.description.setText(announcement.getDescription());
        holder.typeLabel.setText(announcement.getType());

        return convertView;
    }

    private static class ViewHolder {
        ImageView starIcon;
        TextView title;
        TextView description;
        TextView typeLabel;
    }
}


