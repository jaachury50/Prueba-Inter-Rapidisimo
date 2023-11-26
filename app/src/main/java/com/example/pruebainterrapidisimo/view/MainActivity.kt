package com.example.pruebainterrapidisimo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebainterrapidisimo.R
import com.example.pruebainterrapidisimo.adapter.TablesAdapter
import com.example.pruebainterrapidisimo.database.DataBase
import com.example.pruebainterrapidisimo.databinding.ActivityMainBinding
import com.example.pruebainterrapidisimo.model.TableModel
import com.example.pruebainterrapidisimo.viewsmodels.TablesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TablesViewModel
    private lateinit var databaseHelper: DataBase
    private lateinit var adapterTables: TablesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TablesViewModel::class.java]

        setupRecycleView()

        binding.btnConsumeApi.setOnClickListener {

            viewModel.getTables()
            if(!viewModel.error){
                viewModel.listTables.observe(this) {

                    val queries = mutableListOf<TableModel>()

                    it.forEach { tableModel ->
                        try {
                            databaseHelper = DataBase(this)
                            queries.add(tableModel)
                        } catch (e: Exception) {
                            Log.e("Error", "Error al crear la tabla: ${tableModel.nombreTabla}", e)
                        }
                    }

                    val dbHelper = DataBase(this)
                    dbHelper.createTables(queries)

                    adapterTables.listTables = it
                    adapterTables.notifyDataSetChanged()
                    hideError()
                }
            }else{
                showError(0)
            }

        }

        binding.btnSearch.setOnClickListener{
            val searchText = binding.edtFilter.text.toString().trim()

            val filteredList = viewModel.listTables.value?.filter {
                it.nombreTabla.contains(searchText, ignoreCase = true)
            }

            adapterTables.listTables = filteredList ?: emptyList()
            adapterTables.notifyDataSetChanged()
            if (filteredList.isNullOrEmpty()) {
                showError(1)
            }else{
                hideError()
            }
        }

        binding.edtFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()

                val filteredList = viewModel.listTables.value?.filter {
                    it.nombreTabla.contains(searchText, ignoreCase = true)
                }

                adapterTables.listTables = filteredList ?: emptyList()
                adapterTables.notifyDataSetChanged()
                if (filteredList.isNullOrEmpty()) {
                    showError(1)
                }else{
                    hideError()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        showError(0)
    }

    private fun setupRecycleView() {
        binding.recyclerTables.layoutManager = LinearLayoutManager(this)
        adapterTables = TablesAdapter(this, arrayListOf())
        binding.recyclerTables.adapter = adapterTables
    }

    private fun showError(type: Int){
        if(type == 1){
            binding.tvErrorText.text = getString(R.string.errorText2)
        }else{
            binding.tvErrorText.text = getString(R.string.errorText)
        }
        binding.linearError.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.linearError.visibility = View.GONE
    }

}
