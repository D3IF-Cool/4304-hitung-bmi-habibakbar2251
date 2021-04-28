package org.d3if4304.week10.ui.riwayat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if4304.week10.db.BMIDAO

class RiwayatViewModelFactory(

    private val db: BMIDAO

) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RiwayatViewModel::class.java)) {
            return RiwayatViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}