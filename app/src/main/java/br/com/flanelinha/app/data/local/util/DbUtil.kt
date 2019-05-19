package br.com.flanelinha.app.data.local.util

import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.data.local.MyDatabase
import io.reactivex.Observable

class DbUtil(context: Context) {

    private val database: MyDatabase = MyDatabase
            .getInstance(context.applicationContext)!!
    private val carDao = database.carDao()

    fun insertCar(car: Car): Observable<Boolean> {
        return Observable.fromCallable({
            carDao.insert(car)
            true
        })
    }

    fun deleteCar(car: Car): Observable<Boolean> {
        return Observable.fromCallable({
            carDao.delete(car)
            true
        })
    }

    fun updateCar(car: Car): Observable<Boolean> {
        return Observable.fromCallable({
            carDao.update(car)
            true
        })
    }

    fun loadCars(): Observable<List<Car>> {
        return Observable.fromCallable({
            carDao.loadCars()
        })
    }

}