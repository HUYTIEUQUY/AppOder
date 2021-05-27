package com.example.appoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mviewpager;
    private BottomNavigationView mbottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mviewpager=findViewById(R.id.view_pager);
        mbottomNavigationView=findViewById(R.id.botton_navigration);

        ViewTabAdapter adapter = new ViewTabAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewpager.setAdapter(adapter);
        mviewpager.setOffscreenPageLimit(2); //Để tải thêm 2 tab

        //xử lý sự kiện vuốt vào qua chuyển tab
        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.tab_home).setChecked(true);
                        break;//phải có break vì newes không có thì nó chạy tràn vào các id tiếp theo

                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.tab_order).setChecked(true);
                        break;


                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.tab_user).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_home:
                        mviewpager.setCurrentItem(0);
                        break;

                    case R.id.tab_order:
                        mviewpager.setCurrentItem(1);
                        break;

                    case R.id.tab_user:
                        mviewpager.setCurrentItem(2);
                        break;


                }

                return true;

            }
        });


    }



}