package org.techtown.festivity.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.techtown.festivity.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment{
    ViewPager viewPager;
    TabLayout tabs;

    String[] months = {
            "J","F","M","A",
            "M", "J","J","A",
            "S","O","N","D"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        viewPager = root.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(12);

        MyPagerAdapter adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        for(int i = 0; i<12; i++){
            adapter.addItem(new ChildFragment());
        }
        viewPager.setAdapter(adapter);

        tabs = root.findViewById(R.id.tabs);
        for(int i=0;i<months.length;i++){
            tabs.addTab(tabs.newTab().setText(months[i]));
        }

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                switch (position){
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                    case 4:
                        viewPager.setCurrentItem(4);
                        break;
                    case 5:
                        viewPager.setCurrentItem(5);
                        break;
                    case 6:
                        viewPager.setCurrentItem(6);
                        break;
                    case 7:
                        viewPager.setCurrentItem(7);
                        break;
                    case 8:
                        viewPager.setCurrentItem(8);
                        break;
                    case 9:
                        viewPager.setCurrentItem(9);
                        break;
                    case 10:
                        viewPager.setCurrentItem(10);
                        break;
                    case 11:
                        viewPager.setCurrentItem(11);
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return root;
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}
