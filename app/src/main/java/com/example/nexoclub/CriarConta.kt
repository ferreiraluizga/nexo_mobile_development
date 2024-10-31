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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nexoclub.roomDB.Cliente
import com.example.nexoclub.ui.theme.buttonGreen
import com.example.nexoclub.viewModel.ClienteViewModel

@Composable
fun CriarContaScreen(modifier: Modifier = Modifier, viewModel: ClienteViewModel, navController: NavController) {

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cpfEmail by remember { mutableStateOf("") }

    val cliente = Cliente(nome, telefone, cpfEmail)

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
                text = "Nome Completo",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            TextField(
                value = nome,
                onValueChange = { nome = it },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Telefone",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            TextField(
                value = telefone,
                onValueChange = {
                    if (it.length < 12){
                        telefone = it
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PhoneVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CPF ou E-Mail",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
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
                    if (nome.isBlank() || telefone.isBlank() || cpfEmail.isBlank()) {
                        Toast.makeText(context, "Preencha todos os campos para realizar cadastro", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.upsertCliente(cliente)
                        Toast.makeText(context, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                        nome = ""
                        telefone = ""
                        cpfEmail = ""
                        navController.navigate("Login")
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
                Text(text = "Cadastrar", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
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
                        append("Já possui cadastro? Clique e entre na sua conta")
                    }
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Login") }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

class PhoneVisualTransformation : VisualTransformation {

    // (xx) xxxxx-xxxx
    override fun filter(text: AnnotatedString): TransformedText {

        val phoneMask = text.text.mapIndexed { index, c ->
            when(index) {
                0 -> "($c"
                1 -> "$c) "
                6 -> "$c-"
                else -> c
            }
        }.joinToString(separator = "")

        return TransformedText(
            AnnotatedString(phoneMask),
            PhoneOffsetMapping
        )
    }

    object PhoneOffsetMapping : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset > 6 -> offset + 4
                offset > 1 -> offset + 3
                offset > 0 -> offset + 1
                else -> offset
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset >= 11 -> offset - 4
                offset >= 5 -> offset - 3
                offset == 4 -> offset - 2
                offset >= 2 -> offset - 1
                else -> offset
            }
        }

    }
}
