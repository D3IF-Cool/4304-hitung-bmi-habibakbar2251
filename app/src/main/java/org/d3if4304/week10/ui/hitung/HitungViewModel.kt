package org.d3if4304.week10.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4304.week10.data.HasilBmi
import org.d3if4304.week10.data.KategoriBmi
import org.d3if4304.week10.db.BMIEntity
import org.d3if4304.week10.db.BMIDAO

class HitungViewModel(private val db: BMIDAO) : ViewModel() {


    // Hasil BMI bisa null jika pengguna belum menghitung BMI
    private val hasilBmi = MutableLiveData<HasilBmi?>()

    // Navigasi akan bernilai null ketika tidak bernavigasi
    private val navigasi = MutableLiveData<KategoriBmi?>()

    // Variabel ini sudah berupa LiveData (tidak mutable),
    // sehingga tidak perlu dijadikan private

    fun hitungBmi(berat: String, tinggi: String, isMale: Boolean) {
        val tinggiCm = tinggi.toFloat() / 100
        val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
        val kategori = if (isMale) {
            when {
                bmi < 20.5 -> KategoriBmi.KURUS
                bmi >= 27.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        } else {
            when {
                bmi < 18.5 -> KategoriBmi.KURUS
                bmi >= 25.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        }
        hasilBmi.value = HasilBmi(bmi, kategori)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBmi = BMIEntity(
                    berat = berat.toFloat(),
                    tinggi = tinggi.toFloat(),
                    isMale = isMale
                )
                db.insert(dataBmi)
            }
        }
    }

    fun mulaiNavigasi() {
        navigasi.value = hasilBmi.value?.kategori
    }
    fun selesaiNavigasi() {
        navigasi.value = null
    }
    fun getHasilBmi() : LiveData<HasilBmi?> = hasilBmi

    fun getNavigasi() : LiveData<KategoriBmi?> = navigasi
}