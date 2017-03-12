package hellow.mobapde.com.helloworld.Adapters;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hellow.mobapde.com.helloworld.R;

/**
 * Created by Mine V3 on 12/03/2017.
 */

public class CustomSwipeAdapter extends PagerAdapter{
    int[] imageResources = {R.drawable.puerto_prinsesa, R.drawable.luneta, R.drawable.seven_eleven};
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context context){
        this.context = context;

    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.swipe_layout, container, false);

        ImageView ivSwipeItem = (ImageView) itemView.findViewById(R.id.iv_swipe_item);
        TextView tvFeaturedAdvTitle = (TextView) itemView.findViewById(R.id.tv_featured_adv_title);

        ivSwipeItem.setImageResource(imageResources[position]);
        tvFeaturedAdvTitle.setText("Adventure Name Here"); //Instead of img resource we could use adventures instead

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
