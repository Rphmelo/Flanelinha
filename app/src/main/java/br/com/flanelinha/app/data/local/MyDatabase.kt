package br.com.flanelinha.app.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.data.local.dao.CarDAO

@Database(entities = [Car::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun carDao(): CarDAO

    companion object {
        var instance: MyDatabase? = null

        fun getInstanceDatabase(context: Context): MyDatabase? {
            if(instance == null){
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase.db")
                        .build()
            }
            return instance
        }
    }

}