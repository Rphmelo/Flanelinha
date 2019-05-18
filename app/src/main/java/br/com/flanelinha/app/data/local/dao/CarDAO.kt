package br.com.flanelinha.app.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import br.com.flanelinha.app.cars.model.Car
import io.reactivex.Single

@Dao
interface CarDAO {

    @Insert(onConflict = REPLACE)
    fun save(car: Car): Single<Long>

    @Query("SELECT * FROM car")
    fun loadCars(): LiveData<List<Car>>

    @Update
    fun update(car: Car): Single<Long>

    @Delete
    fun delete(car: Car): Single<Long>

}
