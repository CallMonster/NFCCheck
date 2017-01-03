package com.tj.chaersi.nfccheck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.adapter.PersonalItemAdapter;
import com.tj.chaersi.nfccheck.base.BaseFragment;
import com.tj.chaersi.nfccheck.widget.ListViewShowView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalFragment extends BaseFragment {

    @BindView(R.id.username) TextView username;
    @BindView(R.id.companyName) TextView companyName;
    @BindView(R.id.className) TextView className;
    @BindView(R.id.workName) TextView workName;
    @BindView(R.id.personalItemView) ListViewShowView personalItemView;

    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.leftView) ImageView leftView;
    @BindView(R.id.leftBtn) View leftBtn;

    public PersonalFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.frag_personal, null);
        ButterKnife.bind(this, view);

        PersonalItemAdapter adapter=new PersonalItemAdapter(getActivity());
        personalItemView.setAdapter(adapter);

        titleView.setText("个人中心");
        leftView.setVisibility(View.INVISIBLE);
        leftBtn.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onClickListener(View v) {

    }
}
