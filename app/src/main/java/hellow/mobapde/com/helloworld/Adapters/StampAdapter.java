package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Stamp;
import hellow.mobapde.com.helloworld.R;

/**
 * Created by Mine V3 on 11/03/2017.
 */

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.StampHolder>{

    private static String TAG = "StampAdapter";

    ArrayList<Stamp> stampList;

    public StampAdapter(ArrayList stampList){
        this.stampList = stampList;
    }

    @Override
    public StampHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stamp, parent, false);

        return new StampHolder(v);
    }

    @Override
    public void onBindViewHolder(StampHolder holder, int position) {
        final Stamp stamp = stampList.get(position);

        holder.ivStampImagePreview.setImageBitmap(stamp.getPicture());
        holder.tvStampTitle.setText(stamp.getName());



    }

    @Override
    public int getItemCount() {
        return stampList.size();
    }

    public class StampHolder extends RecyclerView.ViewHolder{
        TextView tvStampTitle;
        ImageView ivStampImagePreview;

        public StampHolder(View itemView) {
            super(itemView);

            tvStampTitle = (TextView) itemView.findViewById(R.id.tv_stamp_title);
            ivStampImagePreview = (ImageView) itemView.findViewById(R.id.iv_stamp_image_preview);
        }
    }



}
