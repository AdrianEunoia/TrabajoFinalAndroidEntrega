package com.adrian.trabajofinalandroid.Adaptadores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdaptadorFragments extends FragmentPagerAdapter {
    // Elementos
    private ArrayList<Fragment> listaFragments;
    private String[] nombres = new String[]{"Agregar título","Mostrar títulos","Buscar título"};

    public AdaptadorFragments(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> listaFragments) {
        super(fm, behavior);
        this.listaFragments = listaFragments;
    }

    public void agregarFragment(Fragment fragment){
        this.listaFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nombres[position];
    }
}
