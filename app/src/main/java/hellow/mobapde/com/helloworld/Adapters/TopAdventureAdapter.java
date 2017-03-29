package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;

/**
 * Created by Mine V3 on 13/03/2017.
 */

public class TopAdventureAdapter extends RecyclerView.Adapter<TopAdventureAdapter.TopAdventureHolder>{

    ArrayList<Adventure> topAdventureList;

    public TopAdventureAdapter(ArrayList<Adventure> topAdventureList){
        this.topAdventureList = topAdventureList;

    }

    @Override
    public TopAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TopAdventureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return topAdventureList.size();
    }

    public class TopAdventureHolder extends RecyclerView.ViewHolder{

        public TopAdventureHolder(View itemView) {
            super(itemView);
        }
    }

}
