package org.d3if4304.week10.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BMI")
data class BMIEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var berat: Float,
    var tinggi: Float,
    var isMale: Boolean
)