package com.example.nexoclub.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexoclub.roomDB.Cliente
import kotlinx.coroutines.launch

class ClienteViewModel(private val repository: Repository): ViewModel() {

    fun upsertCliente(cliente: Cliente) {
        viewModelScope.launch {
            repository.upsertCliente(cliente)
        }
    }

    fun deleteCliente(cliente: Cliente) {
        viewModelScope.launch {
            repository.deleteCliente(cliente)
        }
    }

    fun validarCliente(cpfEmail: String, onResult: (Cliente?) -> Unit) {
        viewModelScope.launch {
            val cliente = repository.validarCliente(cpfEmail)
            onResult(cliente)
        }
    }

    fun buscarPorId(id_cli: Int, onResult: (Cliente?) -> Unit) {
        viewModelScope.launch {
            val cliente = repository.buscarPorId(id_cli)
            onResult(cliente)
        }
    }

}