package feicui.edu.gitdroid.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import feicui.edu.gitdroid.R;

/**
 * Created by hp on 2016/6/30.
 */
public class Pager0 extends FrameLayout{
    public Pager0(Context context) {
        super(context);
        init();
    }

    public Pager0(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
      LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);


    }

}
