package hellow.mobapde.com.helloworld.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import hellow.mobapde.com.helloworld.Beans.Adventure;
import hellow.mobapde.com.helloworld.R;

/**
 * Created by Mine V3 on 05/04/2017.
 */

public class RelatedAdventureAdapter extends RecyclerView.Adapter<RelatedAdventureAdapter.RelatedAdventureHolder>{

    ArrayList<Adventure> relatedAdventureList;

    private RadioButton lastCheckedRB = null;

    private int selectedIndex = -1;

    public RelatedAdventureAdapter(ArrayList<Adventure> relatedAdventureList){
        this.relatedAdventureList = relatedAdventureList;
    }

    @Override
    public RelatedAdventureAdapter.RelatedAdventureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rel_adv, parent, false);
        return new RelatedAdventureHolder(v);
    }

    @Override
    public void onBindViewHolder(RelatedAdventureAdapter.RelatedAdventureHolder holder, int position) {
        final Adventure adventure = relatedAdventureList.get(position);

        holder.ivRelAdvPrev.setImageBitmap(adventure.getPicture());
        holder.ivRelAdvPrev.setBackground(null);

        holder.tvRelAdvName.setText(adventure.getName());

        holder.rBtnRelAdv.setTag(new Integer(position));
        holder.rBtnRelAdv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RadioButton checked_rb = (RadioButton) buttonView.findViewById(R.id.r_btn_rel_adv);
                if (lastCheckedRB != null) {
                    lastCheckedRB.setChecked(false);
                }
                //store the clicked radiobutton

                selectedIndex = (int) buttonView.getTag();

                Log.i("Selected Index", selectedIndex + "");

                lastCheckedRB = checked_rb;
            }
        });


    }

    @Override
    public int getItemCount() {
        return relatedAdventureList.size();
    }

    public class RelatedAdventureHolder extends RecyclerView.ViewHolder{

        RadioButton rBtnRelAdv;
        ImageView ivRelAdvPrev;
        TextView tvRelAdvName;
        LinearLayout llRelAdvContainer;

        public RelatedAdventureHolder(View itemView) {
            super(itemView);

            rBtnRelAdv = (RadioButton) itemView.findViewById(R.id.r_btn_rel_adv);

            ivRelAdvPrev = (ImageView) itemView.findViewById(R.id.iv_rel_adv_prev);

            tvRelAdvName = (TextView) itemView.findViewById(R.id.tv_rel_adv_name);

            llRelAdvContainer = (LinearLayout) itemView.findViewById(R.id.ll_rel_adv_container);

        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
}
