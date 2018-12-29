package com.jjl.demo.draglayout.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjl.demo.draglayout.MainActivity;
import com.jjl.demo.draglayout.R;
import com.jjl.demo.draglayout.view.CustomScrollView;

public class TopDetailProductFragment extends Fragment{
    private View rootView;
    private MainActivity.Direction mPageIndicator;

    public static TopDetailProductFragment newInstance() {
        TopDetailProductFragment fragment = new TopDetailProductFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_product, null);
        TextView textView = rootView.findViewById(R.id.textView);
        textView.setText("Top");
        rootView.setBackgroundColor(Color.GREEN);
        CustomScrollView scrollView =rootView.findViewById(R.id.scrollView);
        scrollView.setAllowDragOnBottom(true);
        return rootView;
    }

    public void setPageIndicator(MainActivity.Direction pageIndicator) {
        //换页初始化
        mPageIndicator = pageIndicator;
    }

}
