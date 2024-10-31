package com.example.nexoclub.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Cliente::class],
    version = 1
)

abstract class ClienteDatabase: RoomDatabase() {
    abstract fun clienteDAO(): ClienteDAO
}