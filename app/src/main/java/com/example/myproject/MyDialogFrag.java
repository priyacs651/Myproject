package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myproject.databinding.FragMyDialogBinding;

public class MyDialogFrag extends DialogFragment {
    FragMyDialogBinding binding;
    AnimalViewModel model;
    OnItemAddListener onItemAddListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_my_dialog, container, false);
        model = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        Animal animal = new Animal();
        binding.setAnimal(animal);
        binding.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        model.addAnimal(binding.getAnimal());
                    }
                }).start();
                dismiss();
                onItemAddListener.onItemAdded();

            }
        });

        binding.btCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_NoTitleBar_Fullscreen);

    }

    public void setAddItemListener(OnItemAddListener addItemListener) {
        this.onItemAddListener = addItemListener;
    }

    interface OnItemAddListener {
        void onItemAdded();
    }


}
