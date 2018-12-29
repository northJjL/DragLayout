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

public class BottomDetailProductFragment extends Fragment{
    private View rootView;
    private MainActivity.Direction mPageIndicator;

    public static BottomDetailProductFragment newInstance() {
        BottomDetailProductFragment fragment = new BottomDetailProductFragment();
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
        textView.setText("Bottom");
        rootView.setBackgroundColor(Color.YELLOW);
        CustomScrollView scrollView =rootView.findViewById(R.id.scrollView);
        scrollView.setAllowDragOnTop(true);
        return rootView;
    }

    public void setPageIndicator(MainActivity.Direction pageIndicator) {
        //换页初始化
        mPageIndicator = pageIndicator;
    }
}
