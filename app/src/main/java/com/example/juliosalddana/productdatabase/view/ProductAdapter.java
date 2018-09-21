package com.example.juliosalddana.productdatabase.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliosalddana.productdatabase.R;
import com.example.juliosalddana.productdatabase.database.model.Product;
import com.example.juliosalddana.productdatabase.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList = new ArrayList<>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.layout_product_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Product product = productList.get(position);
        viewHolder.tvName.setText(product.getName());
        viewHolder.tvDescription.setText(product.getDescription());

        if(product.getImagePath() != null && !product.getImagePath().isEmpty()){
            viewHolder.ivPhoto.setImageBitmap(BitmapFactory.decodeFile(product.getImagePath()));
        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        ImageView ivPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_product_name);
            tvDescription = itemView.findViewById(R.id.tv_product_description);
            ivPhoto = itemView.findViewById(R.id.iv_product);
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
