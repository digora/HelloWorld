package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;

/**
 * Created by Mine V3 on 11/03/2017.
 */

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureHolder>{

    ArrayList<Adventure> adventureList;

    public AdventureAdapter(ArrayList<Adventure> adventureList){
        this.adventureList = adventureList;
    }

    @Override
    public AdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AdventureHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdventureHolder extends RecyclerView.ViewHolder{

        public AdventureHolder(View itemView) {
            super(itemView);
        }
    }

}
