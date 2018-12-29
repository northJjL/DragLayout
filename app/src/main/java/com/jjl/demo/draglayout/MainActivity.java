package com.jjl.demo.draglayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjl.demo.draglayout.fragment.BottomDetailProductFragment;
import com.jjl.demo.draglayout.fragment.TopDetailProductFragment;
import com.jjl.demo.draglayout.view.DragLayout;

public class MainActivity extends AppCompatActivity {

    public DragLayout mDragLayout;
    private TopDetailProductFragment mTopDetailProductFragment;
    private BottomDetailProductFragment mBottomDetailProductFragment;

    public enum Direction {
        Up, Down
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    private void setupView() {
        mDragLayout = findViewById(R.id.draglayout);
        mTopDetailProductFragment = TopDetailProductFragment.newInstance();
        mBottomDetailProductFragment = BottomDetailProductFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.top_detail_product_layout, mTopDetailProductFragment)
                .add(R.id.bottom_detail_product_layout, mBottomDetailProductFragment)
                .commit();
        DragLayout.ShowNextPageNotifier nextIntf = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext(int pageIndex) {
                if (pageIndex == 1) {
                    mTopDetailProductFragment.setPageIndicator(Direction.Down);
                    mBottomDetailProductFragment.setPageIndicator(Direction.Down);
                } else {
                    mTopDetailProductFragment.setPageIndicator(Direction.Up);
                    mBottomDetailProductFragment.setPageIndicator(Direction.Up);
                }
            }
        };
        mDragLayout.setNextPageListener(nextIntf);
    }


}
