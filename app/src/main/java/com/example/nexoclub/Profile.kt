package com.example.nexoclub

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nexoclub.roomDB.Cliente
import com.example.nexoclub.ui.theme.buttonGreen
import com.example.nexoclub.viewModel.ClienteViewModel

@Composable
fun ProfileScreen(id_cli: Int, viewModel: ClienteViewModel, navController: NavController) {
    // Estado para armazenar o cliente
    val clienteState = remember { mutableStateOf<Cliente?>(null) }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    val nomeState = remember { mutableStateOf("") }
    val telefoneState = remember { mutableStateOf("") }
    val cpfEmailState = remember { mutableStateOf("") }

    // Efeito colateral para buscar o cliente
    LaunchedEffect(id_cli) {
        viewModel.buscarPorId(id_cli) { cliente ->
            clienteState.value = cliente
            nomeState.value = cliente?.nome ?: ""
            telefoneState.value = cliente?.telefone ?: ""
            cpfEmailState.value = cliente?.cpfEmail ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Usuário",
                modifier = Modifier.size(120.dp),
                tint = Color(0xFF003366)
            )
            Text(
                text = "Minha Conta",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF003366)
            )
        }

        clienteState.value?.let { cliente ->
            OutlinedTextField(
                value = cliente.id.toString(),
                onValueChange = {},
                label = { Text(text = "Seu Id de Usuário") },
                readOnly = true
            )
            OutlinedTextField(
                value = cliente.nome,
                onValueChange = {},
                label = { Text(text = "Nome Completo") },
                readOnly = true
            )
            OutlinedTextField(
                value = cliente.telefone,
                onValueChange = {},
                label = { Text(text = "Telefone") },
                readOnly = true,
                visualTransformation = PhoneVisualTransformation()
            )
            OutlinedTextField(
                value = cliente.cpfEmail,
                onValueChange = {},
                label = { Text(text = "CPF ou E-Mail") },
                readOnly = true
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    showEditDialog = true
                },
                colors = ButtonColors(
                    containerColor = Color(0xFF003366),
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(text = "Editar")
            }
            Button(
                onClick = {
                    showDeleteDialog = true
                },
                colors = ButtonColors(
                    containerColor = Color(0xFFC62828),
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(text = "Excluir")
            }
            Button(
                onClick = {
                    showExitDialog = true
                },
                colors = ButtonColors(
                    containerColor = Color(0xFF003366),
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(text = "Sair")
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Excluir Conta") },
            text = { Text("Tem certeza de que deseja excluir sua conta?") },
            confirmButton = {
                Button(
                    onClick = {
                        // Lógica de exclusão (por exemplo, chamar um método no viewModel)
                        clienteState.value?.let { cliente ->
                            viewModel.deleteCliente(cliente)
                        }
                        showDeleteDialog = false
                        navController.navigate("Login") {
                            popUpTo("profile") { inclusive = true }
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color(0xFFC62828),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDeleteDialog = false },
                    colors = ButtonColors(
                        containerColor = Color(0xFF003366),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Não")
                }
            }
        )
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Sair da Conta") },
            text = { Text("Tem certeza de que deseja sair da sua conta?") },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        navController.navigate("Login") {
                            popUpTo("profile") { inclusive = true }
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color(0xFFC62828),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showExitDialog = false },
                    colors = ButtonColors(
                        containerColor = Color(0xFF003366),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Não")
                }
            }
        )
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("Editar Conta") },
            text = {
                Column {
                    OutlinedTextField(
                        value = nomeState.value,
                        onValueChange = { nomeState.value = it },
                        label = { Text(text = "Nome Completo") }
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = telefoneState.value,
                        onValueChange = { telefoneState.value = it },
                        label = { Text(text = "Telefone") },
                        visualTransformation = PhoneVisualTransformation()
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = cpfEmailState.value,
                        onValueChange = { cpfEmailState.value = it },
                        label = { Text(text = "CPF ou E-Mail") }
                    )
                }
            },
            confirmButton = {
                val cliente = Cliente(
                    id = id_cli,
                    nome = nomeState.value,
                    telefone = telefoneState.value,
                    cpfEmail = cpfEmailState.value
                )
                Button(
                    onClick = {
                        viewModel.upsertCliente(cliente)
                        showEditDialog = false
                        navController.navigate("Home/${id_cli}") {
                            popUpTo("profile") { inclusive = true }
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color(0xFF1FA123),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Atualizar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showEditDialog = false },
                    colors = ButtonColors(
                        containerColor = Color(0xFFC62828),
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}