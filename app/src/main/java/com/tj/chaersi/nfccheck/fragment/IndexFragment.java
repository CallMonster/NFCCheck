package com.tj.chaersi.nfccheck.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.ShowAllGridLayoutManager;
import com.tj.chaersi.nfccheck.activity.CheckPlanActivity;
import com.tj.chaersi.nfccheck.activity.CheckPointDetailActivity;
import com.tj.chaersi.nfccheck.activity.ErrorUploadActivity;
import com.tj.chaersi.nfccheck.activity.FixErrActivity;
import com.tj.chaersi.nfccheck.activity.Index05_Activity;
import com.tj.chaersi.nfccheck.activity.ResidentCheckActivity;
import com.tj.chaersi.nfccheck.adapter.CheckItemAdapter;
import com.tj.chaersi.nfccheck.base.BaseFragment;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexFragment extends BaseFragment {

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftView) ImageView leftView;
    @BindView(R.id.leftBtn) View leftBtn;

    @BindView(R.id.showAllItemView) RecyclerView showAllItemView;
    @BindView(R.id.topImgBtn) ImageView topImgBtn;
    public IndexFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_index, null);
        ButterKnife.bind(this, view);

        titleView.setText("首页");
        leftView.setVisibility(View.INVISIBLE);
        leftBtn.setVisibility(View.INVISIBLE);

        showAllItemView.setHasFixedSize(true);
        ShowAllGridLayoutManager layoutManager = new ShowAllGridLayoutManager(getActivity(), 3);
        showAllItemView.setLayoutManager(layoutManager);
        CheckItemAdapter adapter=new CheckItemAdapter(getActivity());
        showAllItemView.setAdapter(adapter);
        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                switch (position){
                    case 0:
                        Intent residentIntent=new Intent(getActivity(), ResidentCheckActivity.class);
                        startActivity(residentIntent);
                        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 1:
                        Intent errUploadIntent=new Intent(getActivity(), ErrorUploadActivity.class);
                        startActivity(errUploadIntent);
                        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);

                        break;
                    case 2:
                        Intent errProcessIntent=new Intent(getActivity(), FixErrActivity.class);
                        startActivity(errProcessIntent);
                        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);

                        break;
                    case 3:
                        Intent smartIntent=new Intent(getActivity(), CheckPlanActivity.class);
                        startActivity(smartIntent);
                        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 4:
                        Intent confirmIntent=new Intent(getActivity(), Index05_Activity.class);
                        startActivity(confirmIntent);
                        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                }
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
        topImgBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClickListener(View v) {
    }
}
