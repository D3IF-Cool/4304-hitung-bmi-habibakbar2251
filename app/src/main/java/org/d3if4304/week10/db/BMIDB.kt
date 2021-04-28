package org.d3if4304.week10.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BMIEntity::class], version = 1, exportSchema = false)
abstract class BmiDb : RoomDatabase() {
    abstract val dao: BmiDao
    companion object {
        @Volatile
        private var INSTANCE: BmiDb? = null
        fun getInstance(context: Context): BmiDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) { instance = Room.databaseBuilder(
                    context.applicationContext,
                    BmiDb::class.java,
                    "bmi.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}