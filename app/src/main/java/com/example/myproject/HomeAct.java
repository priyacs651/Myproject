package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.databinding.AnimalsViewHolderBinding;
import com.example.myproject.databinding.HomeActBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAct extends AppCompatActivity {
    private ListOfAminalsFrag frag;
    private HomeActBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home_act);
        addFragment();
        binding.imgAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag.callDialogFrag();
            }
        });

    }

    private void addFragment() {
        binding.tv.setText("Animals");
        frag = new ListOfAminalsFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.FragemtContainer, frag).commit();
    }

    public void callShowDetailsFrag(Animal animal) {
        binding.tv.setText("Animal Details");
        ShowDetailsFrag showDetailsFrag = new ShowDetailsFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Animal",animal);
        showDetailsFrag.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.FragemtContainer, showDetailsFrag).addToBackStack("AnimalDetails").commit();
    }
}