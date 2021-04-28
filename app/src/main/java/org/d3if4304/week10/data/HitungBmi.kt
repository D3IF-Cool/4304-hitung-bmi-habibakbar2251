package org.d3if4304.week10.data

import org.d3if4304.week10.db.BMIEntity

object HitungBmi {
    fun hitung(data: BMIEntity): HasilBmi {
        val tinggiCm = data.tinggi / 100
        val bmi = data.berat / (tinggiCm * tinggiCm)
        val kategori = if (data.isMale) {
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
        return HasilBmi(bmi, kategori)
    }
}
