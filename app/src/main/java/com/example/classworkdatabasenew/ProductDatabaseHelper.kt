package com.example.classworkdatabasenew

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "product.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE product1 (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                price REAL,
                description TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS product1")
        onCreate(db)
    }

    fun insertProduct(product: Product) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", product.name)
            put("price", product.price)
            put("description", product.description) // thêm trường description
        }
        db.insert("product1", null, values)
        db.close()
    }

    fun updateProduct(product: Product) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", product.name)
            put("price", product.price)
            put("description", product.description) // thêm trường description
        }
        db.update("product1", values, "id=?", arrayOf(product.id.toString()))
        db.close()
    }

    fun deleteProduct(id: Int) {
        val db = writableDatabase
        db.delete("product1", "id=?", arrayOf(id.toString()))
        db.close()
    }

    fun getAllProducts(): List<Product> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM product1", null)
        val productList = mutableListOf<Product>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val price = cursor.getDouble(2)
            val description = cursor.getString(3)
            productList.add(Product(id, name, price, description))
        }
        cursor.close()
        db.close()
        return productList
    }
}
