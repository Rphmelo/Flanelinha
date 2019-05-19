package br.com.flanelinha.app.cars.ui.fragments

import android.arch.lifecycle.Observer
import android.content.Context
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
import br.com.flanelinha.app.common.util.ErrorHandler
import kotlinx.android.synthetic.main.fragment_car_list.*

class CarListFragment : Fragment() {

    private lateinit var carViewModel: CarViewModel
    private var cars: List<Car>? = null
    private var carListAdapter: CarsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carViewModel = CarViewModel(activity!!)
        insert_car_button.setOnClickListener({
            ErrorHandler.showInsertUpdateDialog(context!!,null,"Cadastrar", action = {
                car -> carViewModel.insertCar(car)
            })
        })
        loadCars()
        setupRecyclerView()
    }

    private fun loadCars(){
        carViewModel.loadCars()
    }

    private fun setupRecyclerView(){
        rvCars.layoutManager = LinearLayoutManager(context!!)

        carViewModel.cars.observe(this, Observer<List<Car>> {
            cars = it
            carListAdapter = CarsListAdapter(
                    activity!!,
                    cars,
                    onDeleteListener = {
                        adapterPosition ->  deleteItemList(adapterPosition)
                    },
                    onUpdateListener = {
                        adapterPosition ->  updateItemList(adapterPosition)
                    }
            )
            rvCars.adapter = carListAdapter

            if(it!!.isEmpty()){
                container_no_items.visibility = View.VISIBLE
                rvCars.visibility = View.GONE
            } else {
                container_no_items.visibility = View.GONE
                rvCars.visibility = View.VISIBLE
                rvCars.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun openFragment(fragment: Fragment){
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun updateItemList(adapterPosition: Int){
        val car = cars?.get(adapterPosition)

        ErrorHandler.showInsertUpdateDialog(context!!, car, "Atualizar", action = {
            carViewModel.updateCar(car!!)
        })
    }

    private fun deleteItemList(adapterPosition: Int){
        val car = cars?.get(adapterPosition)

        ErrorHandler.showDeleteDialog(activity as Context, car,  deleteAction = {
            carViewModel.deleteCar(car)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        carViewModel.dispose()
    }

}

