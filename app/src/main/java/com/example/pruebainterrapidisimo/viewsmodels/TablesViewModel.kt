package com.example.pruebainterrapidisimo.viewsmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebainterrapidisimo.model.TableModel
import com.example.pruebainterrapidisimo.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TablesViewModel: ViewModel() {
    private var _listTables = MutableLiveData<List<TableModel>>()

    val listTables: LiveData<List<TableModel>> = _listTables
    var error: Boolean = false

    fun getTables(){
        viewModelScope.launch(Dispatchers.IO){
            val response = RetrofitClient.webService.consumeApi("admin")
            if(response.isSuccessful){
                withContext(Dispatchers.Main){
                    _listTables.value = response.body()!!
                }
            }else{
                error = true
            }

        }
    }


}
