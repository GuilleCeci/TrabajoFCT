package com.example.trabajofct.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.trabajofct.Adapters.PagerAdapter;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.example.trabajo1.R;
import com.example.trabajofct.Fragments.ModificarFragment;
import com.example.trabajofct.Modules.Usuarios;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*public class GestionarActivity extends AppCompatActivity {
    public static final int ALUMNOS_FRAGMENT = 0;
    public static final int ASIGNATURAS_FRAGMENT = 1;
    public static final int GRUPOS_FRAGMENT = 2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private DrawerLayout drawerLayout;
    private SharedPreferences preferences;
    private NavigationView navigationView;
    private TextView NombreUsuario2;
    private DatabaseReference firebase;
    private String id;
    private FirebaseAuth autorizacion = FirebaseAuth.getInstance();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);
        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
        drawerLayout = (DrawerLayout) findViewById(R.id.dlayout);
        navigationView= (NavigationView) findViewById(R.id.navview2);
        View headerView = navigationView.getHeaderView(0);
        NombreUsuario2 = (TextView) headerView.findViewById(R.id.nombreusuario2);

        firebase = FirebaseDatabase.getInstance().getReference();

        id = autorizacion.getCurrentUser().getUid();

        firebase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
                NombreUsuario2.setText(""+usuarios.getNombre());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.menu_gestionar:
                        drawerLayout.closeDrawers();
                        Intent intent = new Intent(getApplicationContext(), GestionarActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_modificar:
                        fragment = new ModificarFragment();
                        fragmentTransaction = true;
                        break;

                }

                if (fragmentTransaction) {
                    changeFragment(fragment, menuItem);
                    drawerLayout.closeDrawers();
                }
                return false;
            }
        });


    }

    private void setToolbar() {
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void changeFragment(Fragment fragment, MenuItem menuItem) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle());
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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_logout:
                logOut();
                return true;
            case R.id.menu_forget_logout:
                removeSharedPreferences();
                logOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void removeSharedPreferences()
    {
        preferences.edit().clear().apply();
    }
    private void logOut() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

}*/

