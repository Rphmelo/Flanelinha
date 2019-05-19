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
                    onItemListClick = {
                        adapterPosition ->  updateItemList(adapterPosition)
                    },
                    onItemListLongClick = {
                        adapterPosition ->  deleteItemList(adapterPosition)
                    }
            )
            rvCars.adapter = carListAdapter
            rvCars.adapter?.notifyDataSetChanged()
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

        val args = Bundle()
        val updateFragment = RegisterCarFragment();

        car?.let {
            args.putLong("id", it.id)
            args.putString("model", it.model)
            args.putString("plate", it.plate)
        }

        args.putBoolean("isUpdate", true)

        updateFragment.setArguments(args)

        openFragment(updateFragment)
    }

    private fun deleteItemList(adapterPosition: Int){
        val car = cars?.get(adapterPosition)

        ErrorHandler.showDeleteDialog(activity as Context, car, deleteAction = {
            carViewModel.deleteCar(car)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        carViewModel.dispose()
    }

}

