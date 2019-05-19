package br.com.flanelinha.app.data.local.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import br.com.flanelinha.app.cars.model.Car

@Dao
interface CarDAO {

    @Insert(onConflict = REPLACE)
    fun insert(car: Car)

    @Query("SELECT * FROM car")
    fun loadCars(): List<Car>

    @Update
    fun update(car: Car)

    @Delete
    fun delete(car: Car)

}
