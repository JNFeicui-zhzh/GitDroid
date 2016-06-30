package feicui.edu.gitdroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.edu.gitdroid.commons.ActivityUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;//抽屉(包含内容+侧滑菜单)
    @Bind(R.id.navigationView) NavigationView navigationView;//侧滑菜单视图

    private ActivityUtils activityUtils;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        activityUtils=new ActivityUtils(this);
        //设置navigationView的监听器
        navigationView.setNavigationItemSelectedListener(this);
        //默认第一个menu项为选中(最热门)
        menuItem=navigationView.getMenu().findItem(R.id.github_hot_repo);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //将默认选中项"手动"设置为false
        if (menuItem.isChecked()){
            menuItem.setChecked(false);
        }
        switch (item.getItemId()){
            case R.id.github_hot_repo:
                activityUtils.showToast(R.string.hot_repo);
                break;
            case R.id.arsenal_my_repo:
                activityUtils.showToast(R.string.my_repo);
                break;
            case R.id.tips_daily:
                activityUtils.showToast(R.string.tips_daily);
                break;
        }
        //返回true,代表将该菜单项变为checked状态
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //如NavigationView是开的->关闭
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        //如果NavitionView是关的->退出当前Activity
        else{
            super.onBackPressed();
        }
    }
}
