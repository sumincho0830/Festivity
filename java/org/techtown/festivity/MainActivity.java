package org.techtown.festivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import org.techtown.festivity.ui.Posting;
import org.techtown.festivity.ui.calendar.CalendarFragment;
import org.techtown.festivity.ui.home.HomeFragment;
import org.techtown.festivity.ui.invitations.InvitationsActivity;
import org.techtown.festivity.ui.messages.MessagesActivity;
import org.techtown.festivity.ui.post.PostFragment;
import org.techtown.festivity.ui.profile.ProfileFragment;
import org.techtown.festivity.ui.search.SearchFragment;

import java.util.ArrayList;

/*

!!! ISSUES & To Do's !!!

1. Invitation 안열리고 인텐트 종료됨
2. HomeFragment RecyclerView로
3. Tab 스와이프 시 바뀌도록

 */

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener, OnPostButtonClickListener {
    ViewPager mainView;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    PostFragment postFragment;
    CalendarFragment calendarFragment;
    ProfileFragment profileFragment;
    BottomNavigationView bottomNavigationView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewPager
        mainView = findViewById(R.id.main_viewpager);
        mainView.setOffscreenPageLimit(5);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        homeFragment = new HomeFragment();
        adapter.addItem(homeFragment);
        searchFragment = new SearchFragment();
        adapter.addItem(searchFragment);
        postFragment = new PostFragment();
        adapter.addItem(postFragment);
        calendarFragment = new CalendarFragment();
        adapter.addItem(calendarFragment);
        profileFragment = new ProfileFragment();
        adapter.addItem(profileFragment);

        mainView.setAdapter(adapter);
        mainView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_search);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_post);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.menu_calendar);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //BottomNavView
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.menu_home){
                    mainView.setCurrentItem(0);
                }else if(id == R.id.menu_search){
                    mainView.setCurrentItem(1);
                }else if(id == R.id.menu_post){
                    mainView.setCurrentItem(2);
                }else if(id == R.id.menu_calendar){
                    mainView.setCurrentItem(3);
                }else if(id == R.id.menu_profile){
                    mainView.setCurrentItem(4);
                }
                return true;
            }
        });

        //Actionbar Menu
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); //타이틀은 보이지 않기

        //SMS Permissions
        AutoPermissions.Companion.loadAllPermissions(this, 101);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, @NonNull String[] strings) {
        Toast.makeText(this, "permission denied: "+strings.length, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int i, @NonNull String[] strings) {
        Toast.makeText(this, "permission granted: "+strings.length, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPost(Posting posting) {
        // 이 안에 homeFragment의 메서드를 호출
        homeFragment.post(posting);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        if(curId == R.id.menu_invitations){
            Intent intent = new Intent(getApplicationContext(), InvitationsActivity.class);
            startActivity(intent);
        }else if(curId == R.id.menu_messages){
            Intent intent = new Intent(getApplicationContext(), MessagesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return false;
    }
}