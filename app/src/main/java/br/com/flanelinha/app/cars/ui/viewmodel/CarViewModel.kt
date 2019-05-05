package br.com.flanelinha.app.cars.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.data.local.MyDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class CarViewModel(val context: Context): ViewModel() {

    private val database: MyDatabase = MyDatabase
            .getInstanceDatabase(context.applicationContext)!!
    private val carDao = database.carDao()
    private val executor = Executors.newSingleThreadExecutor()

    var cars: LiveData<List<Car>> =  MutableLiveData<List<Car>>()
    var isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()

    fun loadCars() {
        cars = carDao.loadCars()
    }

    fun saveCar(car: Car){
        executor.execute {
            carDao.save(car)
        }
    }

    fun updateCar(car: Car){
        executor.execute {
            carDao.update(car)
        }
    }

    fun loadCarById(id: Int): Car {
        return carDao.loadCarById(id)
    }

    private fun setLoading(value: Boolean){
//        isLoading = value
    }
}