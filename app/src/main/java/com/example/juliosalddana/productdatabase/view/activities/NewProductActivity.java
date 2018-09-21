package com.example.juliosalddana.productdatabase.view.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.juliosalddana.productdatabase.Constants;
import com.example.juliosalddana.productdatabase.R;
import com.example.juliosalddana.productdatabase.utils.FileUtils;
import com.example.juliosalddana.productdatabase.database.ProductDao;
import com.example.juliosalddana.productdatabase.database.model.Product;
import com.example.juliosalddana.productdatabase.utils.ImageUtils;

import java.io.File;

public class NewProductActivity extends AppCompatActivity {
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private ProductDao productDao;
    private Button btnNewProduct;
    private ImageButton imgBtnProduct;
    private File mImageFile;
    private ImageView ivProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        productDao = new ProductDao(this);
        final EditText etProductName = findViewById(R.id.et_product_name);
        final EditText etProductDescription = findViewById(R.id.et_product_description);
        btnNewProduct = findViewById(R.id.btn_new_product);
        imgBtnProduct = findViewById(R.id.img_btn_product);
        ivProduct = findViewById(R.id.iv_product);



        imgBtnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImageFile = FileUtils.getImageFile(NewProductActivity.this);

                Uri uri = FileProvider.getUriForFile(
                        NewProductActivity.this,
                        Constants.FILE_PROVIDER,
                        mImageFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
            }
        });


        btnNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product = new Product();
                product.setName(etProductName.getText().toString());
                product.setDescription(etProductDescription.getText().toString());
                product.setImagePath(mImageFile.getPath());
                Log.d("START_IMAGE", product.getImagePath());

                long productID = productDao.saveProduct(product);

                if (productID != -1) {
                    Toast.makeText(NewProductActivity.this, "Producto guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewProductActivity.this, "Error guardando producto", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CAPTURE_IMAGE){
            updatePhotoImage();
        }
    }


    /**
     * Mostramos la foto en nuestro ImageView
     */
    private void updatePhotoImage() {
        Bitmap bitmap = ImageUtils.scaleBitmap(mImageFile.getPath(),
                ivProduct.getMaxWidth(),
                ivProduct.getMaxHeight());

        ivProduct.setImageBitmap(bitmap);
    }
}
