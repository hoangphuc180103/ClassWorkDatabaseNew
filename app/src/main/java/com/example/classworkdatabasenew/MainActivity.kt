package com.example.classworkdatabasenew

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: ProductDatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private var productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = ProductDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPrice = findViewById<EditText>(R.id.edtPrice)
        val edtDescription = findViewById<EditText>(R.id.edtDescription)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val name = edtName.text.toString()
            val price = edtPrice.text.toString().toDoubleOrNull() ?: 0.0
            val description = edtDescription.text.toString()
            dbHelper.insertProduct(Product(name = name, price = price, description = description))
            loadData()
        }

        loadData()
    }

    private fun showEditDialog(product: Product) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_product, null)
        val edtName = dialogView.findViewById<EditText>(R.id.edtEditName)
        val edtPrice = dialogView.findViewById<EditText>(R.id.edtEditPrice)
        val edtDescription = dialogView.findViewById<EditText>(R.id.edtEditDescription)

        edtName.setText(product.name)
        edtPrice.setText(product.price.toString())
        edtDescription.setText(product.description)

        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Edit Product")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val newName = edtName.text.toString()
                val newPrice = edtPrice.text.toString().toDoubleOrNull() ?: 0.0
                val newDescription = edtDescription.text.toString()

                val updatedProduct = Product(id = product.id, name = newName, price = newPrice, description = newDescription)
                dbHelper.updateProduct(updatedProduct)
                loadData()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun showDeleteConfirmationDialog(product: Product) {
        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete \"${product.name}\"?")
            .setPositiveButton("Yes") { _, _ ->
                dbHelper.deleteProduct(product.id)
                loadData()
            }
            .setNegativeButton("No", null)
            .create()

        dialog.show()
    }

    private fun loadData() {
        productList = dbHelper.getAllProducts().toMutableList()
        adapter = ProductAdapter(productList,
            onEdit = { product -> showEditDialog(product) },
            onDelete = { product -> showDeleteConfirmationDialog(product) }
        )

        recyclerView.adapter = adapter
    }
}