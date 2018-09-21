package com.example.juliosalddana.productdatabase.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juliosalddana.productdatabase.R;
import com.example.juliosalddana.productdatabase.database.ProductDao;
import com.example.juliosalddana.productdatabase.database.model.Product;
import com.example.juliosalddana.productdatabase.view.ProductAdapter;

public class MainActivity extends AppCompatActivity {
    private ProductDao productDao;
    private RecyclerView rvProducts;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDao = new ProductDao(this);
        rvProducts = findViewById(R.id.rv_products);

        productAdapter = new ProductAdapter();

        rvProducts.setAdapter(productAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));

        Button btnNewProduct = findViewById(R.id.btn_new_product);
        btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        productAdapter.setProductList( productDao.findAllProducts());
        productAdapter.notifyDataSetChanged();
    }
}
