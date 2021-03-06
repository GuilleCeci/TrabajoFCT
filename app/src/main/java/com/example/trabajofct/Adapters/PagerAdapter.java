package com.example.trabajofct.Adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.trabajofct.Fragments.AlumnosFragment;
import com.example.trabajofct.Fragments.AsignaturasFragment;
import com.example.trabajofct.Fragments.GruposFragment;

import static com.example.trabajofct.Activities.GestionarActivity.ALUMNOS_FRAGMENT;
import static com.example.trabajofct.Activities.GestionarActivity.ASIGNATURAS_FRAGMENT;
import static com.example.trabajofct.Activities.GestionarActivity.GRUPOS_FRAGMENT;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;




    private int TabsNumber;


    public PagerAdapter(FragmentManager fm, int TabsNumber) {
        super(fm);
        this.TabsNumber = TabsNumber;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case ALUMNOS_FRAGMENT:
                return new AlumnosFragment();
            case ASIGNATURAS_FRAGMENT:
                return new AsignaturasFragment();
            case GRUPOS_FRAGMENT:
                return new GruposFragment();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return TabsNumber;
    }
}
