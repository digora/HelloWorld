package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;

/**
 * Created by Mine V3 on 13/03/2017.
 */

public class ClosestAdventureAdapter extends RecyclerView.Adapter<ClosestAdventureAdapter.ClosestAdventureHolder>{

    ArrayList<Adventure> closestAdventureList;


    public ClosestAdventureAdapter(ArrayList<Adventure> closestAdventureList){
        this.closestAdventureList = closestAdventureList;
    }

    @Override
    public ClosestAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ClosestAdventureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return closestAdventureList.size();
    }

    public class ClosestAdventureHolder extends RecyclerView.ViewHolder{

        public ClosestAdventureHolder(View itemView) {
            super(itemView);
        }
    }

}
