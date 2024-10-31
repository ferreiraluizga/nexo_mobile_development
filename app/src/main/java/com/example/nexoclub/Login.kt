package com.example.nexoclub

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nexoclub.ui.theme.buttonGreen
import com.example.nexoclub.viewModel.ClienteViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel: ClienteViewModel, navController: NavController) {

    var cpfEmail by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 75.dp, horizontal = 28.dp)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(R.drawable.nexo_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(85.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Digite seu CPF ou E-mail NEXOClub",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            TextField(
                value = cpfEmail,
                onValueChange = { cpfEmail = it },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            val context = LocalContext.current

            Button(
                onClick = {
                    if (cpfEmail.isBlank()) {
                        Toast.makeText(context, "Insira o CPF ou E-Mail para fazer login", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.validarCliente(cpfEmail) { cliente ->
                            if (cliente != null) {
                                Toast.makeText(context, "Bem-vindo(a) de volta " + cliente.nome, Toast.LENGTH_SHORT).show()
                                navController.navigate("Home/${cliente.id}")
                            } else {
                                Toast.makeText(context, "Cliente não encontrado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                colors = ButtonColors(
                    containerColor = buttonGreen,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(55.dp)
            ) {
                Text(text = " Entrar", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Ainda não possui cadastro? Clique e cadastre-se")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("CriarConta") }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


