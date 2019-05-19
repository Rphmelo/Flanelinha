package br.com.flanelinha.app.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.common.constants.DB_NAME
import br.com.flanelinha.app.data.local.dao.CarDAO

@Database(entities = [Car::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    companion object {
        @Volatile private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): MyDatabase {
            return Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    DB_NAME).build()
        }
    }

    abstract fun carDao(): CarDAO

}