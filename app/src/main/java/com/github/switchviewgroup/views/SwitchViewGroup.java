package com.github.switchviewgroup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.github.switchviewgroup.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/1/19.
 */
public class SwitchViewGroup extends RelativeLayout {

    private static final int DURATION = 3000;
    private LinearLayout rootLL;    //显示层
    private TextView firstTV;           //显示文本1
    private TextView secondTV;           //显示文本2

    private Scroller scroller;
    private int index = -1;              //

    private LinearLayout rootAnimLL;     //动画层
    private TextView animFirstTV;          //动画文本1
    private TextView animSecondTV;          //动画文本2

    private boolean isEnd = true;

    private int contentHeight;

    private List<String> datas;


    private OnClickTabListener onClickTabListener;

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            scroll2Next();
//            mHandler.sendEmptyMessageDelayed(0, DURATION);
//        }
//    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            scroll2Next();
            //  mHandler.sendEmptyMessageDelayed(0, DURATION);
            postDelayed(this, DURATION);
        }
    };

    public interface OnClickTabListener {
        void onClickTab(int index);
    }

    public SwitchViewGroup(Context context) {
        this(context, null);
    }

    public SwitchViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        datas = new ArrayList<String>();
        contentHeight = context.getResources().getDimensionPixelSize(R.dimen.vertical_view_group_height);

        scroller = new Scroller(context, new AccelerateDecelerateInterpolator());

        rootLL = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.vertical_view_group_view, null);
        firstTV = (TextView) rootLL.findViewById(R.id.first_content_TV);
        secondTV = (TextView) rootLL.findViewById(R.id.second_content_TV);

        rootAnimLL = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.vertical_view_group_anim_view, null);
        animFirstTV = (TextView) rootAnimLL.findViewById(R.id.anim_first_content_TV);
        animSecondTV = (TextView) rootAnimLL.findViewById(R.id.anim_second_content_TV);

        addView(rootLL);
        addView(rootAnimLL);
        rootLL.setVisibility(VISIBLE);
        rootAnimLL.setVisibility(INVISIBLE);


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //     stopScroll();

                if (onClickTabListener != null && datas != null && datas.size() > 0) {

                    onClickTabListener.onClickTab(index % datas.size());
                }
            }
        });
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        if (isOnce) {
//            startScroll();
//            isOnce = false;
//        }
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            rootAnimLL.scrollTo(0, scroller.getCurrY());
            invalidate();
        } else {
            if (scroller.isFinished() && !isEnd) {
                isEnd = true;
                rootLL.setVisibility(VISIBLE);
                rootAnimLL.setVisibility(INVISIBLE);


            }

        }

    }

    public void scroll2Next() {
        rootAnimLL.scrollTo(0, 0);
        animFirstTV.setText(firstTV.getText());
        animSecondTV.setText(secondTV.getText());
        rootLL.setVisibility(INVISIBLE);
        rootAnimLL.setVisibility(VISIBLE);
        scroller.startScroll(rootAnimLL.getScrollX(), rootAnimLL.getScrollY(), 0, contentHeight, 1000);
        isEnd = false;
        invalidateText();
        invalidate();
    }

    private void invalidateText() {

        index++;

        if (datas != null && datas.size() > 0) {
            firstTV.setText(datas.get(index % datas.size()));

            secondTV.setText(datas.get((index + 1) % datas.size()));
        }

    }

    public void setOnClickTabListener(OnClickTabListener onClickTabListener) {
        this.onClickTabListener = onClickTabListener;
    }

    public void stopScroll() {
        //  mHandler.removeMessages(0);
//        if (!scroller.isFinished()) {
//            scroller.abortAnimation();
//        }

        removeCallbacks(runnable);
    }

    public void startScroll() {

        if (datas != null && datas.size() > 0) {
            if (datas.size() == 1) {
                invalidateText();
            } else {
                stopScroll();
                // mHandler.sendEmptyMessage(0);
                post(runnable);
            }

        }
    }

    public void addData(String data) {
        datas.add(data);
    }

    public void addData(List<String> data) {
        datas.addAll(data);
    }

    @Override
    protected void onDetachedFromWindow() {
        stopScroll();
        super.onDetachedFromWindow();
    }
}
