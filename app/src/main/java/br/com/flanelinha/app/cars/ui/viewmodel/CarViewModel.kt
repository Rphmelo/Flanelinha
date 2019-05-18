package br.com.flanelinha.app.cars.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.common.util.ErrorHandler
import br.com.flanelinha.app.data.local.MyDatabase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class CarViewModel(val context: Context): ViewModel() {

    private val database: MyDatabase = MyDatabase
            .getInstance(context.applicationContext)!!
    private val carDao = database.carDao()
    private val executor = Executors.newSingleThreadExecutor()
    private var dispose: Disposable? = null

    var cars: LiveData<List<Car>> =  MutableLiveData<List<Car>>()
    var isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()

    fun loadCars() {
//        cars = carDao.loadCars()
    }

    fun saveCar(car: Car){
        executor.execute {
            carDao.save(car)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { setLoading(true) }
                    .subscribe(object: SingleObserver<Long> {
                        override fun onSubscribe(d: Disposable) {
                            dispose = d
                        }

                        override fun onSuccess(rowsInserted: Long) {
                            setLoading(false)
                            if(rowsInserted > 0){
                                ErrorHandler().showErrorMessage(context, "Veículo cadastrado com sucesso!!")
                            } else {
                                ErrorHandler().showErrorMessage(context, "Não foi possível cadastrar este veículo!")
                            }
                        }

                        override fun onError(e: Throwable) {
                            setLoading(false)
                            ErrorHandler().showErrorMessage(context, "Não foi possível cadastrar este veículo!")
                        }
                    })
        }
    }

    fun updateCar(car: Car){
        executor.execute {
            carDao.update(car)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { setLoading(true) }
                    .subscribe(object: SingleObserver<Long> {
                        override fun onSubscribe(d: Disposable) {
                            dispose = d
                        }

                        override fun onSuccess(rowsInserted: Long) {
                            setLoading(false)
                            if(rowsInserted > 0){
                                ErrorHandler().showErrorMessage(context, "Veículo atualizado com sucesso!!")
                            } else {
                                ErrorHandler().showErrorMessage(context, "Não foi possível atualizar este veículo!")
                            }
                        }

                        override fun onError(e: Throwable) {
                            setLoading(false)
                            ErrorHandler().showErrorMessage(context, "Não foi possível atualizar este veículo!")
                        }
                    })
        }
    }

    fun deleteCar(car: Car){
        executor.execute {
            carDao.delete(car)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { setLoading(true) }
                    .subscribe(object: SingleObserver<Long> {
                        override fun onSubscribe(d: Disposable) {
                            dispose = d
                        }

                        override fun onSuccess(rowsInserted: Long) {
                            setLoading(false)
                            if(rowsInserted > 0){
                                ErrorHandler().showErrorMessage(context, "Veículo excluído com sucesso!!")
                            } else {
                                ErrorHandler().showErrorMessage(context, "Não foi possível excluir este veículo!")
                            }
                        }

                        override fun onError(e: Throwable) {
                            setLoading(false)
                            ErrorHandler().showErrorMessage(context, "Não foi possível excluir este veículo!")
                        }
                    })
        }
    }

    private fun setLoading(value: Boolean){
//        isLoading.observe() = value
    }

    fun dispose(){
        dispose?.dispose()
    }
}