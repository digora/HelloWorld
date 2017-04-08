package hellow.mobapde.com.helloworld.Adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.R;

/**
 * Created by Mine V3 on 11/03/2017.
 */

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureHolder>{

    private static String TAG = "AdventureAdapter";

    ArrayList<Adventure> adventureList;

    AdventureAdapter.OnAdventureClickListener onAdventureClickListener;

    public AdventureAdapter(ArrayList<Adventure> adventureList){
        this.adventureList = adventureList;

        if(this.adventureList == null){
            Log.d(TAG, "ADAPTER - Adventure List is null");
        }else{
            Log.d(TAG, "ADAPTER - Adventure List is not null");
        }


    }

    @Override
    public AdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_adventure, parent, false);

        if(v != null){
            Log.d(TAG, "ADAPTER - Layout not null");
        }else{
            Log.d(TAG, "ADAPTER - Layout is null!");
        }

        return new AdventureHolder(v);
    }

    @Override
    public void onBindViewHolder(AdventureHolder holder, int position) {
        final Adventure adventure = adventureList.get(position);

        Log.d("TAG", "ADAPTER - AdventureList received with size: " + this.adventureList.size());

        holder.tvAdventureTitle.setText(adventure.getName());
        holder.ivAdventureImagePreview.setImageBitmap(adventure.getPicture());

        holder.llAdventurePopContainer.setTag(adventure);
        holder.llAdventurePopContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdventureClickListener.onAdventureClick(v, (Adventure) v.getTag());
            }
        });

        Log.d(TAG, "ADAPTER - Adventure title is: " + holder.tvAdventureTitle.getText().toString());

        if(holder.ivAdventureImagePreview == null){
            Log.d(TAG, "ADAPTER - Adventure Image is null!");
        }else{
            Log.d(TAG, "ADAPTER - Adventure Image is not null");
        }

    }

    @Override
    public int getItemCount() {
        return adventureList.size();
    }

    public class AdventureHolder extends RecyclerView.ViewHolder{
        TextView tvAdventureTitle;
        ImageView ivAdventureImagePreview;

        LinearLayout llAdventurePopContainer;

        public AdventureHolder(View itemView) {
            super(itemView);
            tvAdventureTitle = (TextView) itemView.findViewById(R.id.tv_adventure_title);

            llAdventurePopContainer = (LinearLayout) itemView.findViewById(R.id.ll_adventure_pop_container);

            ivAdventureImagePreview = (ImageView) itemView.findViewById(R.id.iv_adventure_image_preview);

        }
    }

    public interface OnAdventureClickListener{
        public void onAdventureClick(View view, Adventure a);
    }

    public AdventureAdapter.OnAdventureClickListener getOnAdventureClickListener()    {
        return onAdventureClickListener;
    }

    public void setOnAdventureClickListener(AdventureAdapter.OnAdventureClickListener onAdventureClickListener) {
        this.onAdventureClickListener = onAdventureClickListener;
    }


}
