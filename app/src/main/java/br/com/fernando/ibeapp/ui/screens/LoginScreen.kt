package br.com.fernando.ibeapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fernando.ibeapp.ui.theme.IBEDarkBlue
import br.com.fernando.ibeapp.ui.theme.IBEYellow
import br.com.fernando.ibeapp.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewmodel: LoginViewModel = viewModel()) {

    val emailState = viewmodel.email.collectAsState()
    val email = emailState.value

    val passwordState = viewmodel.password.collectAsState()
    val password = passwordState.value

    val roundedCornerShape = RoundedCornerShape(20.dp)

    Scaffold(
        bottomBar = {BottomBar(
            buttons = listOf(
                BottomBarButton("Agenda", Icons.Filled.AccountBox),
                BottomBarButton("Avisos", Icons.Filled.Notifications),
                BottomBarButton("Recados", Icons.Filled.DateRange)
            )
        )
        },
        containerColor = IBEDarkBlue
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Igreja Batista Esperança",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White

            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { viewmodel.onEmailChange(it) },
                label = { Text("E-mail", color = Color.White) },
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow

                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { viewmodel.onPasswordChange(it) },
                label = { Text("Senha", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                shape = roundedCornerShape,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = IBEYellow,
                    unfocusedBorderColor = IBEYellow

                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Esqueci senha",
                modifier = Modifier.clickable { /* Ação de esqueci senha */ },
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewmodel.onLoginClick() },
                Modifier
                    .width(270.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = IBEYellow),
            ) {
                Text("ACESSAR")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text= "Não tem conta? Cadastre-se",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
            )
        }
    }
}

@Composable
fun BottomBar(buttons: List<BottomBarButton>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        buttons.forEach { button ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = button.icon, contentDescription = button.text, tint = IBEYellow)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = button.text, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen()
}

data class BottomBarButton(val text: String, val icon: ImageVector)