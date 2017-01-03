package com.tj.chaersi.nfccheck.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.FragPageAdapter;
import com.tj.chaersi.nfccheck.fragment.IndexFragment;
import com.tj.chaersi.nfccheck.fragment.PersonalFragment;
import com.tj.chaersi.nfccheck.widget.ShadowColorView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.frag_viewpager) ViewPager fragViewpager;
    @BindView(R.id.id_indicator_one) ShadowColorView idIndicatorOne;
    @BindView(R.id.id_indicator_two) ShadowColorView idIndicatorTwo;

    private ArrayList<ShadowColorView> indicatorsArr;
    private ArrayList<Fragment> fragmentsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        indicatorsArr=new ArrayList<ShadowColorView>();
        indicatorsArr.add(idIndicatorOne);
        indicatorsArr.add(idIndicatorTwo);

        idIndicatorOne.setOnClickListener(this);
        idIndicatorTwo.setOnClickListener(this);
        idIndicatorOne.setIconAlpha(1.0f);

        fragmentsArr=new ArrayList<Fragment>();
        IndexFragment frag_one=new IndexFragment();
        fragmentsArr.add(frag_one);
        PersonalFragment frag_two=new PersonalFragment();
        fragmentsArr.add(frag_two);

        FragPageAdapter adapter=new FragPageAdapter(getSupportFragmentManager(),fragmentsArr);
        fragViewpager.setAdapter(adapter);
        fragViewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ShadowColorView left = indicatorsArr.get(position);
            ShadowColorView right = indicatorsArr.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 重置所有btn
     */
    private void resetTabBtn(){
        for(ShadowColorView item:indicatorsArr){
            item.setIconAlpha(0);
        }
    }

    @OnClick({R.id.id_indicator_one, R.id.id_indicator_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_indicator_one:
                resetTabBtn();
                indicatorsArr.get(0).setIconAlpha(1.0f);
                fragViewpager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                resetTabBtn();
                indicatorsArr.get(1).setIconAlpha(1.0f);
                fragViewpager.setCurrentItem(1, false);
                break;
        }
    }
}
