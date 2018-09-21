package com.example.juliosalddana.productdatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.juliosalddana.productdatabase.database.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    StoreDatabaseHelper mDbHelper;

    public ProductDao(Context context) {
        this.mDbHelper = new StoreDatabaseHelper(context);
    }


    String[] productColums = {
            ProductContract.ProductEntry._ID,
            ProductContract.ProductEntry.COLUMN_NAME,
            ProductContract.ProductEntry.COLUMN_DESCRIPTION
    };

    public long saveProduct(Product product) {

        //Trae la base de datos en modo de escritura
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Crea un mapa de valores donde las llaves son los nombres de las columnas de la tabla
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME, product.getName());
        values.put(ProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());

        //Creamos una nueva fila en la base de datos
        return db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);

    }

    public Product findProductByID(long id) {
        Product product = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = ProductContract.ProductEntry._ID + " = ?";

//        String sortOrder =
//                ProductContract.ProductEntry.COLUMN_NAME + " DESC";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,   // La tabla que vamos a consultar
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
        }

        return product;

    }


    public List<Product> findAllProducts(){

        List<Product> productList = new ArrayList<>();
        Cursor cursor = mDbHelper.getReadableDatabase()
                .query(ProductContract.ProductEntry.TABLE_NAME, productColums,null,
                        null,null, null, null);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Product product = new Product();
                product.setId(cursor.getLong(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                productList.add(product);
            }
        }

        return productList;
    }

    public int updateProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_NAME, product.getName());
        values.put(ProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());

        // updating row
        return mDbHelper.getWritableDatabase().update(ProductContract.ProductEntry.TABLE_NAME, values,
                ProductContract.ProductEntry._ID + "=?",new String[] { String.valueOf(product.getId())});
    }

    // Deleting Employee
    public void removeProduct(Product product) {

        mDbHelper.getWritableDatabase().delete(ProductContract.ProductEntry.TABLE_NAME,
                ProductContract.ProductEntry._ID+ "=" + product.getId(), null);
    }
}
