package com.github.switchviewgroup;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.github.switchviewgroup.views.SwitchViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwitchViewGroup mSwitchViewGroup;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitchViewGroup = (SwitchViewGroup) findViewById(R.id.switchViewGroup);


        for (int i = 0; i < 3; i++) {
            datas.add("trumpet____" + i);
        }
        mSwitchViewGroup.addData(datas);
        mSwitchViewGroup.startScroll();
        mSwitchViewGroup.setOnClickTabListener(new SwitchViewGroup.OnClickTabListener() {
            @Override
            public void onClickTab(int index) {
                Snackbar.make(mSwitchViewGroup, datas.get(index), Snackbar.LENGTH_SHORT).show();
            }
        });

    }


}
