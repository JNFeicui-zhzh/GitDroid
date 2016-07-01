package feicui.edu.gitdroid.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2016/7/1.
 */
public class HotRepoPagerAdapter extends FragmentPagerAdapter {

    private final List<String> languages;//列表
    public HotRepoPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        languages=new ArrayList<>();
        languages.add("java 1");
        languages.add("java 2");
        languages.add("java 3");
        languages.add("java 4");
        languages.add("java 5");
        languages.add("java 6");
        languages.add("java 7");

    }

    @Override
    public Fragment getItem(int position) {
        return RepoListFragment.getInstance(languages.get(position));

    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return languages.get(position);
    }
}
