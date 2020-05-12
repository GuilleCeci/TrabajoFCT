package com.example.trabajofct.Activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.trabajofct.Adapters.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.Toolbar;
import com.example.trabajo1.R;
import com.google.android.material.tabs.TabLayout;

public class GestionarActivity extends AppCompatActivity {
    public static final int ALUMNOS_FRAGMENT = 0;
    public static final int ASIGNATURAS_FRAGMENT = 1;
    public static final int GRUPOS_FRAGMENT = 2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);
        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
    }

    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Alumnos"));
        tabLayout.addTab(tabLayout.newTab().setText("Asignaturas"));
        tabLayout.addTab(tabLayout.newTab().setText("Grupos"));
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTabLayout(final ViewPager viewPager) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

}

