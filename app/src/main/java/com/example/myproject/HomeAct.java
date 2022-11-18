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

    void addFragment() {
        frag = new ListOfAminalsFrag();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.FragemtContainer, frag).addToBackStack(null).commit();
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
        List<Animal> list = new ArrayList<>();


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            AnimalsViewHolderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.animals_view_holder, parent, false);
            return new MyViewHolder(binding);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void updateAnimalList(List<Animal> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            AnimalsViewHolderBinding binding;

            public MyViewHolder(@NonNull AnimalsViewHolderBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void bind() {
                binding.setAnimal(list.get(getAdapterPosition()));
            }
        }
    }
}