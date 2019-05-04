package br.com.flanelinha.app.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Update
import br.com.flanelinha.app.cars.model.Car

@Dao
interface CarDAO {

    @Insert(onConflict = REPLACE)
    fun save(car: Car)

    @Query("SELECT * FROM car")
    fun loadCars(): LiveData<List<Car>>

    @Update
    fun update(car: Car)

}