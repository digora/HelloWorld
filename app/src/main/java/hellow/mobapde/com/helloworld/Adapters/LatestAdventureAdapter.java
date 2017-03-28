package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;

/**
 * Created by Mine V3 on 13/03/2017.
 */

public class LatestAdventureAdapter extends RecyclerView.Adapter<LatestAdventureAdapter.LatestAdventureHolder>{

    ArrayList<Adventure> latestAdventureList;

    public LatestAdventureAdapter(ArrayList<Adventure> latestAdventureList){
        this.latestAdventureList = latestAdventureList;
    }

    @Override
    public LatestAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LatestAdventureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return latestAdventureList.size();
    }

    public class LatestAdventureHolder extends RecyclerView.ViewHolder{

        public LatestAdventureHolder(View itemView) {
            super(itemView);
        }
    }
}
