package br.com.flanelinha.app.cars.ui.fragments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.flanelinha.app.R
import br.com.flanelinha.app.cars.model.Car
import br.com.flanelinha.app.cars.ui.CarsListAdapter
import br.com.flanelinha.app.cars.ui.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.fragment_car_list.*

class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadCars()
        setupRecyclerView()
    }

    private fun loadCars(){
        carViewModel = CarViewModel(activity!!)
        carViewModel.loadCars()
    }

    private fun setupRecyclerView(){
        rvCars.layoutManager = LinearLayoutManager(context!!)
        carViewModel.cars.observe(this, Observer<List<Car>> {
            rvCars.adapter = CarsListAdapter(activity!!, it!!)
        })
    }

}
