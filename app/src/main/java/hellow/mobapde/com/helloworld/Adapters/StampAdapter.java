package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import hellow.mobapde.com.helloworld.Beans.Stamp;

/**
 * Created by Mine V3 on 11/03/2017.
 */

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.StampHolder>{

    @Override
    public StampHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(StampHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StampHolder extends RecyclerView.ViewHolder{

        public StampHolder(View itemView) {
            super(itemView);
        }
    }


}
