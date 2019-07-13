package com.example.hitam_toastmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AhCounterResultsAdapter extends BaseAdapter {

    TextView spkrName, ah, um, shrt, lng, and, so, wrd, rem;

    public Context context;
    public List<AhCounterSingleItem> singleMemberItem;


    public AhCounterResultsAdapter(@NonNull Context context, int resource, @NonNull List<AhCounterSingleItem> objects) {
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

        AhCounterSingleItem currentMemberItem = (AhCounterSingleItem) getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.ahcounter_results, parent, false);
        }

        spkrName = listItemView.findViewById(R.id.ahcListSpeakerName);
        spkrName.setText(currentMemberItem.getSpkrname());

        ah = listItemView.findViewById(R.id.ahcListAhCount);
        ah.setText("Ah: "+currentMemberItem.getAh());

        um = listItemView.findViewById(R.id.ahcListUmCount);
        um.setText("Um/Uh: "+currentMemberItem.getUm());

        shrt = listItemView.findViewById(R.id.ahcListShortCount);
        shrt.setText("Short: "+currentMemberItem.getShrt());

        lng = listItemView.findViewById(R.id.ahcListLongCount);
        lng.setText("Long: "+currentMemberItem.getLng());

        and = listItemView.findViewById(R.id.ahcListAndCount);
        and.setText("And: "+currentMemberItem.getAnd());

        so = listItemView.findViewById(R.id.ahcListSoCount);
        so.setText("So: "+currentMemberItem.getSo());

        wrd = listItemView.findViewById(R.id.ahcListWord);
        wrd.setText("Favourite Word: "+currentMemberItem.getWord());

        rem = listItemView.findViewById(R.id.ahcListRemark);
        rem.setText("Remarks: "+currentMemberItem.getRem());

        return listItemView;
    }
}
