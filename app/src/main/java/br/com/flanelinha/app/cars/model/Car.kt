package br.com.flanelinha.app.cars.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Car(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val plate: String,
        val model: String
)