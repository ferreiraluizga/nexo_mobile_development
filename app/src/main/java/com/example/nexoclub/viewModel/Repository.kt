package com.example.nexoclub.viewModel

import com.example.nexoclub.roomDB.Cliente
import com.example.nexoclub.roomDB.ClienteDatabase

class Repository(private val db: ClienteDatabase) {
    suspend fun upsertCliente(cliente: Cliente) {
        db.clienteDAO().upsertCliente(cliente)
    }

    suspend fun deleteCliente(cliente: Cliente) {
        db.clienteDAO().deleteCliente(cliente)
    }

    suspend fun validarCliente(cpfEmail: String): Cliente? {
        return db.clienteDAO().validarCliente(cpfEmail)
    }

    suspend fun buscarPorId(id_cli: Int): Cliente? {
        return db.clienteDAO().buscarPorId(id_cli)
    }
}