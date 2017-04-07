package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
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
 * Created by Mine V3 on 06/04/2017.
 */

public class MoreAdventureAdapter extends RecyclerView.Adapter<MoreAdventureAdapter.MoreAdventureHolder>{

    ArrayList<Adventure> moreAdventureList;

    MoreAdventureAdapter.OnAdventureClickListener onAdventureClickListener;


    public MoreAdventureAdapter(ArrayList<Adventure> moreAdventureList){
        this.moreAdventureList = moreAdventureList;
    }

    @Override
    public MoreAdventureAdapter.MoreAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_more_adventures, parent, false);
        return new MoreAdventureAdapter.MoreAdventureHolder(v);
    }

    @Override
    public void onBindViewHolder(MoreAdventureHolder holder, int position) {
        final Adventure adventure = moreAdventureList.get(position);


        holder.tvMoreAdventureTitle.setText(adventure.getName());
        holder.ivMoreAdventureImage.setImageBitmap(adventure.getPicture());

        holder.tvMoreAdventureDifficulty.setText("Default"); //BACKEND LORDS KAYO NA BAHALA DITO

        holder.llMoreAdvContainer.setTag(adventure);
        holder.llMoreAdvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdventureClickListener.onAdventureClick(v, (Adventure) v.getTag());
            }
        });

    }


    @Override
    public int getItemCount() {
        return moreAdventureList.size();
    }

    public class MoreAdventureHolder extends RecyclerView.ViewHolder{

        LinearLayout llMoreAdvContainer;

        TextView tvMoreAdventureTitle;
        TextView tvMoreAdventureDifficulty;

        ImageView ivMoreAdventureImage;

        public MoreAdventureHolder(View itemView) {
            super(itemView);

            llMoreAdvContainer = (LinearLayout) itemView.findViewById(R.id.ll_more_adv_container);

            tvMoreAdventureTitle = (TextView) itemView.findViewById(R.id.tv_more_adventure_title);

            tvMoreAdventureDifficulty = (TextView)itemView.findViewById(R.id.tv_more_adventure_difficulty);

            ivMoreAdventureImage = (ImageView) itemView.findViewById(R.id.iv_more_adventure_image);



        }
    }

    public interface OnAdventureClickListener{
        public void onAdventureClick(View view, Adventure a);
    }

    public MoreAdventureAdapter.OnAdventureClickListener getOnAdventureClickListener()    {
        return onAdventureClickListener;
    }

    public void setOnAdventureClickListener(MoreAdventureAdapter.OnAdventureClickListener onAdventureClickListener) {
        this.onAdventureClickListener = onAdventureClickListener;
    }


}
