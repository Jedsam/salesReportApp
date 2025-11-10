package com.example.frontendinternship.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.usecase.iface.ILoginUseCase
import com.example.frontendinternship.ui.components.RoundedButton
import com.example.frontendinternship.ui.components.RoundedTextField
import com.example.frontendinternship.ui.components.MyScaffold
import com.example.frontendinternship.ui.components.WifiOnorOff
import com.example.frontendinternship.ui.navigation.Screen
import com.example.frontendinternship.ui.theme.FrontendInternshipTheme
import com.example.frontendinternship.ui.theme.LocalDimensions
import com.example.frontendinternship.ui.theme.LocalPadding
import com.example.frontendinternship.utils.APIOperationStateEnum

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    MyScaffold(
        navController = navController,
        topBarRightSideContent = {
            WifiOnorOff(isOn = true)
        },
        screenText = "Login",
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalDimensions.current.viewExtrasLargePlus),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            ) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = LocalPadding.current.VeryTiny),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
                RoundedButton(
                    buttonText = "Login",
                    onButtonPress = {
                        viewModel.loginRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primary,
                )

                RoundedButton(
                    buttonText = "Register",
                    onButtonPress = {
                        navController.navigate(Screen.Register.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalPadding.current.Small)
                        .height(LocalDimensions.current.viewNormalPlus),
                    borderColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        }) { innerPadding ->
        if (
            uiState.loginLoadingState == APIOperationStateEnum.FAILURE
        ) {
            AlertDialog(
                onDismissRequest = { viewModel.closeOperatingWindow() },
                title = { Text("Login unsuccessful") },
                text = { Text("Invalid username or password") },
                confirmButton = {
                    TextButton(onClick = { viewModel.closeOperatingWindow() }) {
                        Text("OK")
                    }
                },
            )
        }
        if (
            uiState.loginLoadingState == APIOperationStateEnum.SUCCESS
        ) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.closeOperatingWindow()
                    navController.navigate(Screen.Catalog)
                },
                title = { Text("Login successful") },
                text = { Text("Returning back to the catalog") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.closeOperatingWindow()
                        navController.popBackStack(Screen.Catalog.route,
                            inclusive = false
                        )
                    }) {
                        Text("OK")
                    }
                },
            )
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.Normal),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(
                modifier = Modifier
                    .height(LocalDimensions.current.viewBig)
            )
            RoundedTextField(
                textFieldInformation = "Username",
                textValue = uiState.currentUser.username,
                onFieldValueChange = { newText ->
                    if (newText.length < 20)
                        viewModel.updateUsername(newText)
                },
                keyboardType = KeyboardType.Text,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
            )
            Spacer(
                modifier = Modifier
                    .height(LocalDimensions.current.viewTiny)
            )
            RoundedTextField(
                textFieldInformation = "Password",
                textValue = uiState.currentUser.password,
                onFieldValueChange = { newText ->
                    if (newText.length < 16)
                        viewModel.updatePassword(newText)
                },
                keyboardType = KeyboardType.Decimal,
                textFieldModifier = Modifier.fillMaxWidth(0.8f),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    FrontendInternshipTheme {
        LoginScreen(
            navController = rememberNavController(),
            viewModel = LoginViewModel_Factory.newInstance(
                FakeLoginUseCase()
            )
        )
    }
}

class FakeLoginUseCase : ILoginUseCase {
    override suspend fun invoke(userModel: UserModel): Boolean {
        return true
    }
}

