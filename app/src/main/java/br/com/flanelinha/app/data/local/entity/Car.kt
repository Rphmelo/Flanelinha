package br.com.flanelinha.app.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Car(
        @PrimaryKey
        var id: Int = 0,
        var model: String = "",
        var plate: String = ""
)