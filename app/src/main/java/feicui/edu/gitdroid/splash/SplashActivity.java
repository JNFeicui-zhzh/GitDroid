package feicui.edu.gitdroid.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import feicui.edu.gitdroid.home.MainActivity;
import feicui.edu.gitdroid.R;
import feicui.edu.gitdroid.commons.ActivityUtils;

/**
 * 首页面,第一次启动时进入的页面
 */

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.btnLogin) Button mBtnLogin;
    @Bind(R.id.btnEnter) Button mBtnEnter;

    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        activityUtils=new ActivityUtils(this);
    }
    @OnClick(R.id.btnLogin)
    public void login(){
        Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.btnEnter)
    public void enter(){
        activityUtils.startActivity(MainActivity.class);

    }
}
