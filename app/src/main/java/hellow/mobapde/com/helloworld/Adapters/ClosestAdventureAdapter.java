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
 * Created by Mine V3 on 13/03/2017.
 */

public class ClosestAdventureAdapter extends RecyclerView.Adapter<ClosestAdventureAdapter.ClosestAdventureHolder>{

    ArrayList<Adventure> closestAdventureList;

    OnAdventureClickListener onAdventureClickListener;


    public ClosestAdventureAdapter(ArrayList<Adventure> closestAdventureList){
        this.closestAdventureList = closestAdventureList;
    }

    @Override
    public ClosestAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_catalog_adventure, parent, false);
        return new ClosestAdventureHolder(v);
    }

    @Override
    public void onBindViewHolder(ClosestAdventureHolder holder, int position) {
        final Adventure adventure = closestAdventureList.get(position);

        holder.tvAdventureCatalogTitle.setText(adventure.getName());
        holder.ivAdventureCatalogImage.setImageBitmap(adventure.getPicture());

        holder.llAdventureListItem.setTag(adventure);
        holder.llAdventureListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdventureClickListener.onAdventureClick(v, (Adventure) v.getTag());
            }
        });





    }

    @Override
    public int getItemCount() {
        return closestAdventureList.size();
    }

    public class ClosestAdventureHolder extends RecyclerView.ViewHolder{

        TextView tvAdventureCatalogTitle;
        ImageView ivAdventureCatalogImage;
        LinearLayout llAdventureListItem;

        public ClosestAdventureHolder(View itemView) {
            super(itemView);

            tvAdventureCatalogTitle = (TextView) itemView.findViewById(R.id.tv_adventure_catalog_title);

            ivAdventureCatalogImage = (ImageView) itemView.findViewById(R.id.iv_adventure_catalog_image);

            llAdventureListItem = (LinearLayout) itemView.findViewById(R.id.ll_adventure_list_item);
        }
    }

    public interface OnAdventureClickListener{
        public void onAdventureClick(View view, Adventure a);
    }

    public OnAdventureClickListener getOnAdventureClickListener()    {
        return onAdventureClickListener;
    }

    public void setOnAdventureClickListener(OnAdventureClickListener onAdventureClickListener) {
        this.onAdventureClickListener = onAdventureClickListener;
    }


}
