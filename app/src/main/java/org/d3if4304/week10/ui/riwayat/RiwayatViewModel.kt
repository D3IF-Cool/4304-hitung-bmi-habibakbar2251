package org.d3if4304.week10.ui.riwayat

import androidx.lifecycle.ViewModel
import org.d3if4304.week10.db.BMIDAO

class RiwayatViewModel(db: BMIDAO) :ViewModel() {

    val data = db.getLastBmi()
}