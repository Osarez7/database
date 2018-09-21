package com.example.juliosalddana.productdatabase.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juliosalddana.productdatabase.R;
import com.example.juliosalddana.productdatabase.database.ProductDao;
import com.example.juliosalddana.productdatabase.database.model.Product;

public class NewProductActivity extends AppCompatActivity {
    ProductDao productDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        productDao = new ProductDao(this);
        final EditText etProductName = findViewById(R.id.et_product_name);
        final EditText etProductDescription = findViewById(R.id.et_product_description);
        Button btnNewProduct = findViewById(R.id.btn_new_product);


        btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product = new Product();
                product.setName(etProductName.getText().toString());
                product.setDescription(etProductDescription.getText().toString());

                long productID = productDao.saveProduct(product);

                if (productID != -1) {
                    Toast.makeText(NewProductActivity.this, "Producto guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewProductActivity.this, "Error guardando producto", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
