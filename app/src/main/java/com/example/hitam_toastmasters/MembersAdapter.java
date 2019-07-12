package com.example.hitam_toastmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MembersAdapter extends BaseAdapter implements Filterable {

    TextView name, role, level;
    ImageView photo;

    public Context context;
    public List<SingleMemberItem> singleMemberItem;
    public List<SingleMemberItem> mOrig;


    public MembersAdapter(@NonNull Context context, int resource, @NonNull List<SingleMemberItem> objects) {
        super();
        this.context = context;
        this.singleMemberItem = objects;
    }


    @Override
    public int getCount() { return singleMemberItem.size(); }
    @Override
    public Object getItem(int i) { return singleMemberItem.get(i); }
    @Override
    public long getItemId(int i) { return i; }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        SingleMemberItem currentMemberItem = (SingleMemberItem) getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.pick_profile_list, parent, false);
        }

        name = listItemView.findViewById(R.id.memberName);
        name.setText(currentMemberItem.getName());

        role = listItemView.findViewById(R.id.memberRole);
        role.setText(currentMemberItem.getRole());

        level = listItemView.findViewById(R.id.memberLevel);
        level.setText(currentMemberItem.getLevel());

        String photoUrl = "https://firebasestorage.googleapis.com/v0/b/hitamtoastmasters.appspot.com/o/hitam_tm_members%2F"
                +currentMemberItem.getPhotoName().trim()+"?alt=media&token=e62da836-27a7-4b20-a535-79c087100f8f";
        photo = listItemView.findViewById(R.id.memberPic);
        Picasso.get().load(photoUrl).resize(350, 350).into(photo);

        return listItemView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<SingleMemberItem> results = new ArrayList<>();
                if (mOrig == null)
                    mOrig = singleMemberItem;
                if (charSequence != null) {
                    if (mOrig != null && mOrig.size() > 0) {
                        for (final SingleMemberItem g : mOrig) {
                            if (g.getName().toLowerCase().contains(charSequence.toString()) ||
                                    g.getLevel().toLowerCase().contains(charSequence.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                singleMemberItem = (ArrayList<SingleMemberItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
