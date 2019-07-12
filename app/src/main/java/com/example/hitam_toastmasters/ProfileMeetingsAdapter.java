package com.example.hitam_toastmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileMeetingsAdapter extends BaseAdapter {


    TextView name, role, date;
    ImageView ribbon;

    public Context context;
    public List<ProfileSingleMeetingItem> profileSingleMeetingItems;


    public ProfileMeetingsAdapter(@NonNull Context context, int resource, @NonNull List<ProfileSingleMeetingItem> objects) {
        super();
        this.context = context;
        this.profileSingleMeetingItems = objects;
    }


    @Override
    public int getCount() { return profileSingleMeetingItems.size(); }
    @Override
    public Object getItem(int i) { return profileSingleMeetingItems.get(i); }
    @Override
    public long getItemId(int i) { return i; }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ProfileSingleMeetingItem currentMemberItem = (ProfileSingleMeetingItem) getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.profile_meeting_item, parent, false);
        }

        name = listItemView.findViewById(R.id.profileMeetingName);
        name.setText(currentMemberItem.getMeetingName());

        role = listItemView.findViewById(R.id.profileMeetingRole);
        role.setText(currentMemberItem.getRolePlayed());

        date = listItemView.findViewById(R.id.profileMeetingDate);
        date.setText(currentMemberItem.getDate());

        ribbon = listItemView.findViewById(R.id.profileMeetingRibbon);
        if (currentMemberItem.getRibbonEarned().equals("Yes")) {
            ribbon.setVisibility(View.VISIBLE);
        } else {
            ribbon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
