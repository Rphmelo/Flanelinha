package br.com.flanelinha.app.common.extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import br.com.flanelinha.app.cars.model.Car

//fun RecyclerView.addOnItemClickListener(onClickListener: AdapterView.OnItemClickListener) {
//    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
//        override fun onChildViewDetachedFromWindow(view: View?) {
//            view?.setOnClickListener(null)
//        }
//
//        override fun onChildViewAttachedToWindow(view: View?) {
//            view?.setOnClickListener({
//                val holder = getChildViewHolder(view)
//                onClickListener.onItemClick(onClickListener, view, holder.adapterPosition,)
//            })
//        }
//    })
//}