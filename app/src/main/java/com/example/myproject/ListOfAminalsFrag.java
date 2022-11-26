package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myproject.databinding.FragListOfAminalsBinding;

import java.util.List;

public class ListOfAminalsFrag extends Fragment implements MyDialogFrag.OnItemAddListener, AnimalAdapter.OnItemClickListener {
    private FragListOfAminalsBinding binding;
    private MyDialogFrag dialogFrag;
    private AnimalViewModel viewModel;
    private AnimalAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_list_of_aminals, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        initAdapter();
        initObserver();
        viewModel.getAnimal();
        return binding.getRoot();
    }

    private void initAdapter() {
        adapter = new AnimalAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        MySwipeCallback mySwipeCallback = new MySwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mySwipeCallback);
        itemTouchHelper.attachToRecyclerView(binding.recyclerView);
        binding.recyclerView.setAdapter(adapter);
    }

    void initObserver() {
        viewModel.animalMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                adapter.updateAnimalList(animals);
            }
        });
    }

    void callDialogFrag() {
        dialogFrag = new MyDialogFrag();
        FragmentManager fragmentManager = getChildFragmentManager();
        dialogFrag.show(fragmentManager, "frag");
        dialogFrag.setAddItemListener(this);
    }

    @Override
    public void onItemAdded() {
        viewModel.getAnimal();
    }

    @Override
    public void callAnimalDetails(Animal animal) {
        ((HomeAct) getActivity()).callShowDetailsFrag(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        viewModel.removeAnimal(animal);
    }
}
