package org.d3if4304.week10.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BmiDao {
    @Insert
    fun insert(BMI : BMIEntity)
    @Query("SELECT * FROM BMI ORDER BY id DESC LIMIT 1")
    fun getLastBmi(): LiveData<BMIEntity?>
}
