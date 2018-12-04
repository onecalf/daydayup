package com.zhj.demo1.weibo;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhj.demo1.R;

import java.util.ArrayList;
import java.util.List;


public class HotFragment extends Fragment {
    ChildViewPager viewPager;
    TabLayout tabLayout;

    List<Fragment> fragments = new ArrayList<>();
    List<String> tableTitles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hot, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        String[] tags = new String[]{"推荐", "附近", "榜单", "美女", "摄影", "科技", "社会", "新时代", "汽车", "明星", "搞笑",};
        for (String tag : tags) {
            fragments.add(CommonFragment.newInstance(tag));
            tableTitles.add(tag);
        }
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tableTitles.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }
}
