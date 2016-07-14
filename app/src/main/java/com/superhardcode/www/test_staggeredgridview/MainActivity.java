package com.superhardcode.www.test_staggeredgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etsy.android.grid.StaggeredGridView;
import com.superhardcode.www.test_staggeredgridview.adapter.PhotographyListAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridView listview;
    private PhotographyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<HashMap<String, Object>> photoList = new ArrayList<HashMap<String, Object>>();
        for(int i=0; i<9; i++) {

            HashMap<String, Object> photoObj = new HashMap<String, Object>();
            HashMap<String, Object> imgData = new HashMap<String, Object>();
            imgData.put("1x", "");
            imgData.put("2x", "");
            imgData.put("3x", "");

            if(i%2 == 0)
                imgData.put("4x", "http://c7.alamy.com/comp/D0BX98/queen-sirikit-of-thailand-born-mom-rajawongse-sirikit-kitiyakara-D0BX98.jpg");
            else
                imgData.put("4x", "http://media.gettyimages.com/photos/king-bhumibol-and-queen-sirikit-of-thailand-at-kings-beeches-their-picture-id97545562");

            photoObj.put("img", imgData);
            photoList.add(photoObj);
        }

        listview = (StaggeredGridView) findViewById(R.id.photo_gridview);
        adapter = new PhotographyListAdapter(this, listview);
        adapter.setData(photoList);
    }
}
