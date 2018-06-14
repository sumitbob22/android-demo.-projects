package com.example.sumitbobade.uiassignment;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by sumitbobade on 08/03/18.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private int image_resource[]={
                                    R.drawable.images_3,
                                    R.drawable.images_4,R.drawable.images_6,
                                    R.drawable.images_7
                                };
        private Context ctx;

        private LayoutInflater layoutInflater;

        CustomPagerAdapter(Context obj)
        {
            ctx=obj;
        }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

//            layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View item_view=layoutInflater.inflate(R.layout.swap_layout,container,false);
//        ImageView imageview=(ImageView) item_view.findViewById(R.id.image_view);
//        imageview.setImageResource(image_resource[position]);
//        container.addView(item_view);
//        return item_view;

        ImageView imageView=new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(image_resource[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((ImageView)object);
    }
}
