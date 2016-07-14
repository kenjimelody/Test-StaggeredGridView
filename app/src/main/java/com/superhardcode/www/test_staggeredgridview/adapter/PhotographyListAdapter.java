package com.superhardcode.www.test_staggeredgridview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.superhardcode.www.test_staggeredgridview.R;
import com.superhardcode.www.test_staggeredgridview.utils.Global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Thanisak Piyasaksiri on 7/13/2016 AD.
 */
public class PhotographyListAdapter {

    private Context context;
    private ListAdapter adapter;
    private StaggeredGridView listViewObj;

    private int screenWidth = 0;
    private int halfofscreenwidth = 0;
    private Transformation transformation;

    private List<HashMap<String, Object>> photographyListObj;

    public PhotographyListAdapter(Context context, StaggeredGridView listViewObj) {

        this.context = context;
        this.listViewObj = listViewObj;
        this.photographyListObj = new ArrayList<HashMap<String, Object>>();

        this.screenWidth = Global.getScreenWidth(context);
        this.halfofscreenwidth = this.screenWidth / 2;

        transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {

                int targetWidth = halfofscreenwidth;

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        this.adapter = new ListAdapter(this.context, R.layout.row_photography, this.photographyListObj);
        this.listViewObj.setAdapter(this.adapter);
    }

    public void setData(List<HashMap<String, Object>> transectionListObj) {

        this.photographyListObj.clear();
        for(int i=0; i<transectionListObj.size(); i++) {
            this.photographyListObj.add((HashMap<String, Object>) transectionListObj.get(i));
        }

        this.adapter.notifyDataSetChanged();
    }

    static class ViewHolder {

        ImageView row_photo_img;
    }

    class ListAdapter extends ArrayAdapter<HashMap<String, Object>> {

        Context context;
        private int resId = 0;
        private List<HashMap<String, Object>> items;

        public ListAdapter(Context context, int textViewResourceId, List<HashMap<String, Object>> items) {

            super(context, textViewResourceId, items);
            this.items = items;
            this.context = context;
            this.resId = textViewResourceId;
        }

        public int getCount() {
            return items.size();
        }

        public HashMap<String, Object> getItem(int position) {
            return items.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            View v = convertView;
            final int positions = position;

            if (convertView == null) {

                LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(this.resId, null);

                holder = new ViewHolder();
                holder.row_photo_img = (ImageView) v.findViewById(R.id.row_photo_img);
                v.setTag(holder);

            } else {
                holder = (ViewHolder) v.getTag();
            }

            final HashMap<String, Object> o = (HashMap<String, Object>) items.get(positions);

            HashMap<String, Object> imgSet = (HashMap<String, Object>) o.get("img");
            String imgPath = String.valueOf(imgSet.get("4x"));

            Picasso.with(context).load(imgPath).transform(transformation).into(holder.row_photo_img);

            return v;
        }
    }

}
