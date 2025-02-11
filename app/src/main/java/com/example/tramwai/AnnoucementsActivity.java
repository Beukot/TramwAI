package com.example.tramwai;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import java.util.ArrayList;

public class AnnouncementsActivity extends AppCompatActivity {
    private ListView announcementsList;
    private ArrayList<Announcement> announcements;
    private Button addAnnouncementButton;
    private static final int EDIT_ANNOUNCEMENT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        announcementsList = findViewById(R.id.announcementsList);
        addAnnouncementButton = findViewById(R.id.addAnnouncementButton);

        // Initialize sample data
        announcements = new ArrayList<>();
        announcements.add(new Announcement("George Droyd", "Nowy projekt rzadu - George D..."));
        announcements.add(new Announcement("Nowa Piekarnia", "Pani Basia na ul. Basi Piekarnic..."));
        announcements.add(new Announcement("Hala Targowa", "Hala Targowa zostala zamkniet..."));
        announcements.add(new Announcement("Most Siennicki", "Z powodu nieznanych problem..."));
        announcements.add(new Announcement("Kup reklamę", "Gdańsk - Miasto które zarabia..."));

        AnnouncementAdapter adapter = new AnnouncementAdapter(this, announcements);
        announcementsList.setAdapter(adapter);

        // Handle item clicks
        announcementsList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AnnouncementsActivity.this, EditAnnouncementActivity.class);
            intent.putExtra("title", announcements.get(position).getTitle());
            intent.putExtra("description", announcements.get(position).getDescription());
            intent.putExtra("position", position);
            startActivityForResult(intent, EDIT_ANNOUNCEMENT_REQUEST);
        });

        addAnnouncementButton.setOnClickListener(v -> {
            Intent intent = new Intent(AnnouncementsActivity.this, EditAnnouncementActivity.class);
            startActivityForResult(intent, EDIT_ANNOUNCEMENT_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ANNOUNCEMENT_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            int position = data.getIntExtra("position", -1);

            if (position != -1) {
                // Update existing announcement
                announcements.get(position).setTitle(title);
                announcements.get(position).setDescription(description);
            } else {
                // Add new announcement
                announcements.add(new Announcement(title, description));
            }
            ((AnnouncementAdapter) announcementsList.getAdapter()).notifyDataSetChanged();
        }
    }
}

