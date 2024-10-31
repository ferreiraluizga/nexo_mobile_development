package com.example.nexoclub.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    val nome: String,
    val telefone: String,
    val cpfEmail: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
