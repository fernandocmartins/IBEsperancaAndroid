package br.com.fernando.ibeapp.ui.screens

import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fernando.ibeapp.ui.theme.IBEDarkBlue
import br.com.fernando.ibeapp.ui.theme.IBEYellow
import br.com.fernando.ibeapp.viewmodel.RegisterViewModel
import java.util.Calendar

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = viewModel()) {

    val emailState = viewModel.email.collectAsState()
    val email = emailState.value
    val nameState = viewModel.name.collectAsState()
    val name = nameState.value

    val birthDateState = viewModel.birthDate.collectAsState()
    val birthDate = birthDateState.value

    val passwordState = viewModel.password.collectAsState()
    val password = passwordState.value

    val confirmPasswordState = viewModel.confirmPassword.collectAsState()
    val confirmPassword = confirmPasswordState.value

    var showDatePicker by remember { mutableStateOf(false) }

    val roundedCornerShape = RoundedCornerShape(20.dp)

    Scaffold(
        containerColor = IBEDarkBlue
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Igreja Batista Esperança",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de E-mail
            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("E-mail", color = Color.White) },
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Nome
            OutlinedTextField(
                value = name,onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Nome", color = Color.White) },
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Data de Nascimento
            OutlinedTextField(
                value = birthDate,
                onValueChange = { viewModel.onBirthDateChange(it) },
                label = { Text("Data de Nascimento", color = Color.White) },
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow
                ),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Calendário",
                            tint = IBEYellow
                        )
                    }
                }
            )

            if (showDatePicker) {
                val context = LocalContext.current
                AndroidView(
                    factory = {
                        View(it).apply {DatePickerDialog(
                            context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                                viewModel.onBirthDateChange("$dayOfMonth/${month + 1}/$year")
                            },
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                        ).show()
                        }
                    },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Senha
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Senha", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Confirmação de Senha
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = { Text("Confirme a Senha", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botão Cadastrar
            Button(
                onClick = { viewModel.onRegisterClick() },
                Modifier
                    .width(270.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = IBEYellow)
            ) {
                Text("CADASTRAR")
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Já tem conta? Login",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                modifier = Modifier.clickable { /* Ação de Login */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}