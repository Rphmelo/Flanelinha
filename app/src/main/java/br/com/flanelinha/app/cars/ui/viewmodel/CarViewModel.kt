package br.com.flanelinha.app.cars.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.common.util.DialogUtil
import br.com.flanelinha.app.data.local.util.DbUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class CarViewModel(val context: Context): ViewModel() {

    private val dbUtil = DbUtil(context)
    private val executor = Executors.newSingleThreadExecutor()
    private var dispose: Disposable? = null

    var cars: MutableLiveData<List<Car>> =  MutableLiveData()

    fun loadCars() {
        dbUtil.loadCars()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { setLoading(true) }
                .subscribe({
                    this.cars.setValue(it)
                })
    }

    fun insertCar(car: Car){
        executor.execute {
            dbUtil.insertCar(car)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { setLoading(true) }
                    .subscribe({
                        if(it){
                            DialogUtil.showMessageDialog(context, "Veículo cadastrado com sucesso!!")
                            loadCars()
                        } else {
                            DialogUtil.showMessageDialog(context, "Não foi possível cadastrar este veículo!")
                        }
                    })
        }
    }

    fun updateCar(car: Car){
        executor.execute {
            dbUtil.updateCar(car)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { setLoading(true) }
                    .subscribe({
                        if(it){
                            DialogUtil.showMessageDialog(context, "Veículo atualizado com sucesso!!")
                            loadCars()
                        } else {
                            DialogUtil.showMessageDialog(context, "Não foi possível atualizar este veículo!")
                        }
                    })
        }
    }

    fun deleteCar(car: Car?){
        executor.execute {
            car?.let {
                dbUtil.deleteCar(it)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe { setLoading(true) }
                        .subscribe({
                            if(it){
                                DialogUtil.showMessageDialog(context, "Veículo deletado com sucesso!!")
                                loadCars()
                            } else {
                                DialogUtil.showMessageDialog(context, "Não foi possível deletar este veículo!")
                            }
                        })
            }
        }
    }

    private fun setLoading(value: Boolean){

    }

    fun dispose(){
        dispose?.dispose()
    }
}
