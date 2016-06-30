package feicui.edu.gitdroid.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.animation.ArgbEvaluator;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import feicui.edu.gitdroid.R;
import feicui.edu.gitdroid.pager.Pager2;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by hp on 2016/6/30.
 */
public class SplashPagerFragment extends Fragment{
    @Bind(R.id.content)
    FrameLayout mFrameLayout;//当前页面布局,用于显示背景颜色的渐变
    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.indicator) CircleIndicator mIndicator;//指示器(下方的小页面)

    @BindColor(R.color.colorGreen) int colorGreen;//viewPager压面对应背景颜色
    @BindColor(R.color.colorRed) int colorRed;//viewPager压面对应背景颜色
    @BindColor(R.color.colorYellow) int colorYellow;//viewPager压面对应背景颜色

    @Bind(R.id.layoutPhone) FrameLayout layoutPhone;//手机Layout
    @Bind(R.id.ivPhoneBlank) ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont) ImageView ivPhoneFont;

    private SplashPagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        adapter=new SplashPagerAdapter(getContext());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageChangeListener);//监听
        viewPager.addOnPageChangeListener(phoneViewHandle);
        mIndicator.setViewPager(viewPager);
    }

    private final ViewPager.OnPageChangeListener phoneViewHandle=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //viewPager 在第一个页面和第二个页面之间
            if(position==0){
                float scale=0.3f+positionOffset*0.5f;
                layoutPhone.setScaleX(scale);
                layoutPhone.setScaleY(scale);
                //正在移动过程中，fone实时的变化
                ivPhoneFont.setAlpha(positionOffset);
                //在移动过程中，有一个平移的动画
                int scroll= (int)  ((positionOffset-1) * 400);
                layoutPhone.setTranslationX(scroll);
                return;
            }

            //ViewPager 在第二个页面和第三个页面之间时(总是为1)
            //手机要和ViewPager 一起平移
            if (position==1){
                layoutPhone.setTranslationX(-positionOffsetPixels);
                return;
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //此监听器主要负责背景颜色的渐变，和最后一个页面视图动画的现实
    private final ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        //ARGB取值器
        final ArgbEvaluator mEvaluator=new ArgbEvaluator();

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //当前scroll的比例: positionOffset
            //start颜色:做一个就行
            //end颜色:做一个就行
            if (position==0){
                int color= (int)mEvaluator.evaluate(positionOffset,colorGreen,colorRed);
                mFrameLayout.setBackgroundColor(color);
                return;
            }
            if (position==1){
                int color= (int)mEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                mFrameLayout.setBackgroundColor(color);
                return;
            }

        }

        @Override
        public void onPageSelected(int position) {
            if (position==2){
                Pager2 pager2= (Pager2) adapter.getView(2);
                pager2.showAnimation();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
