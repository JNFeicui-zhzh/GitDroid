package feicui.edu.gitdroid.repo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import feicui.edu.gitdroid.R;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by hp on 2016/7/1.
 * 用来显示不同语言的仓库列表Fragment
 */
public class RepoListFragment extends Fragment{
    @Bind(R.id.lvRepos) ListView lvRepos;
    //用于下拉刷新
    @Bind(R.id.ptrClassicFrameLayout) PtrClassicFrameLayout mPtrClassicFrameLayout;
    private ArrayAdapter<String> adpater;
    private List<String> datas=new ArrayList<>();

    private static int count;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //只是为了测试
        for (int i=1;i<=10;i++){
            count++;
            datas.add("我是第"+count+"条数据");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        adpater=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);

        lvRepos.setAdapter(adpater);

        //这是下拉刷新核心代码
        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadData(20);
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void loadData(final int size){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //只是为了测试
                try {
                    datas.clear();
                    for (int i = 1; i <= size; i++) {
                        count++;
                        datas.add("我是第" + count + "条数据");
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //找到UI线程完成视图工作
                mPtrClassicFrameLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        //添加刷新数据
                        adpater.clear();
                        adpater.addAll(datas);
                        adpater.notifyDataSetChanged();
                        //下拉刷新完成
                        mPtrClassicFrameLayout.refreshComplete();
                    }
                });

            }
        }).start();


    }
    public static RepoListFragment getInstance(String language){
        RepoListFragment fragment=new RepoListFragment();
        Bundle args =new Bundle();
        args.putSerializable("key_language",language);
        fragment.setArguments(args);
        return fragment;
    }
}

