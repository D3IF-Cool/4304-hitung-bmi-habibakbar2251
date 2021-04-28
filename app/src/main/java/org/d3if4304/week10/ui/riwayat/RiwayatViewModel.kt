package org.d3if4304.week10.ui.riwayat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4304.week10.db.BMIDAO

class RiwayatViewModel(private val db: BMIDAO) :ViewModel() {

    val data = db.getLastBmi()

    fun deleteData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}