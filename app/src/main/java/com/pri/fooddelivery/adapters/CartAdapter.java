package com.pri.fooddelivery.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.pri.fooddelivery.data.CartItem;
import com.pri.fooddelivery.databinding.ItemCartBinding;


public class CartAdapter extends ListAdapter<CartItem, CartAdapter.ViewHolder> {
    private ItemClickListener itemClickListener;

    public CartAdapter(ItemClickListener itemClickListener) {
        super(new ProductDiff());
        this.itemClickListener = itemClickListener;
    }

    public static class ProductDiff extends DiffUtil.ItemCallback<CartItem> {

        @Override
        public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem.getCartId() == newItem.getCartId() && oldItem.getCount() == newItem.getCount();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = getItem(position);
        holder.setData(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding binding;

        public ViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(CartItem cartItem) {
            if (cartItem != null) {
                binding.setData(cartItem);
                binding.btDelete.setOnClickListener(v -> itemClickListener.onItemDelete(cartItem));
                binding.tilCount.setEndIconOnClickListener(v -> {
                    if (cartItem.getCount() < 10) {
                        cartItem.setCount(cartItem.getCount() + 1);
                        itemClickListener.onItemUpdate(cartItem);
                    }

                });
                binding.tilCount.setStartIconOnClickListener(v -> {
                    if (cartItem.getCount() > 1) {
                        cartItem.setCount(cartItem.getCount() - 1);
                        itemClickListener.onItemUpdate(cartItem);
                    }
                });
            }
        }
    }

    public interface ItemClickListener {
        void onItemDelete(CartItem cartItem);

        void onItemUpdate(CartItem cartItem);
    }
}
