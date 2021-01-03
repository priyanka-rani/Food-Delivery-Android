package com.pri.fooddelivery.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.pri.fooddelivery.adapters.ProductListAdapter;
import com.pri.fooddelivery.data.Product;
import com.pri.fooddelivery.databinding.FragmentMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
public class PlaceholderFragment extends Fragment {

    private static final String ARG_CATEGORY = "category";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(String category) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        String category;
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
            pageViewModel.setCategory(category);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        pageViewModel.getProductList().observe(getViewLifecycleOwner(), products -> {
            ProductListAdapter adapter = new ProductListAdapter(products, new ProductListAdapter.ItemClickListener() {
                @Override
                public void onItemClick(Product product) {
                    Snackbar.make(binding.getRoot(), "Product Successfully added to Cart", Snackbar.LENGTH_LONG).show();
                    pageViewModel.addProduct(product);
                }
            });
            binding.rvItems.setAdapter(adapter);

        });
        return binding.getRoot();
    }
}