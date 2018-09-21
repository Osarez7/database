package com.example.juliosalddana.productdatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.juliosalddana.productdatabase.database.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    StoreDatabaseHelper mDbHelper;

    public ProductDao(Context context) {
        this.mDbHelper = new StoreDatabaseHelper(context);
    }


    String[] productColums = {
            ProductContract._ID,
            ProductContract.COLUMN_NAME,
            ProductContract.COLUMN_DESCRIPTION,
            ProductContract.COLUMN_IMAGE_PATH
    };

    public long saveProduct(Product product) {

        //Trae la base de datos en modo de escritura
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Crea un mapa de valores donde las llaves son los nombres de las columnas de la tabla
        ContentValues values = new ContentValues();
        values.put(ProductContract.COLUMN_NAME, product.getName());
        values.put(ProductContract.COLUMN_DESCRIPTION, product.getDescription());
        values.put(ProductContract.COLUMN_IMAGE_PATH, product.getImagePath());

        //Creamos una nueva fila en la base de datos
        return db.insert(ProductContract.TABLE_NAME, null, values);

    }

    public Product findProductByID(long id) {
        Product product = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = ProductContract._ID + " = ?";

//        String sortOrder =
//                ProductContract.ProductEntry.COLUMN_NAME + " DESC";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                ProductContract.TABLE_NAME,   // La tabla que vamos a consultar
                productColums,             //  Un array con las columnas que vamos a retornar
                selection,              //  La condicion de la consulta
                selectionArgs,          // los argumentso para la condicion
                null,                   // no agrupar las columnas
                null,                   // no filtrar por grupos de columnas
                null               // el orden de los resultados
        );

        if (cursor != null) {
            cursor.moveToFirst();

            product = new Product();
            product.setId(cursor.getLong(0));
            product.setName(cursor.getString(1));
            product.setDescription(cursor.getString(2));
            product.setImagePath(cursor.getString(3));
        }

        return product;

    }


    public List<Product> findAllProducts(){

        List<Product> productList = new ArrayList<>();
        Cursor cursor = mDbHelper.getReadableDatabase()
                .query(ProductContract.TABLE_NAME, productColums,null,
                        null,null, null, null);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Product product = new Product();
                product.setId(cursor.getLong(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                Log.d("THE_IMAGE", "" + cursor.getString(3));
                product.setImagePath(cursor.getString(3));
                productList.add(product);
            }
        }

        return productList;
    }

    public int updateProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(ProductContract.COLUMN_NAME, product.getName());
        values.put(ProductContract.COLUMN_DESCRIPTION, product.getDescription());
        values.put(ProductContract.COLUMN_IMAGE_PATH, product.getImagePath());

        // updating row
        return mDbHelper.getWritableDatabase().update(ProductContract.TABLE_NAME, values,
                ProductContract._ID + "=?",new String[] { String.valueOf(product.getId())});
    }

    // Deleting Employee
    public void removeProduct(Product product) {

        mDbHelper.getWritableDatabase().delete(ProductContract.TABLE_NAME,
                ProductContract._ID+ "=" + product.getId(), null);
    }
}
