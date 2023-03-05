package com.gen.userdataapp.presentation.ui.userinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gen.userdataapp.domain.models.UserData
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

const val USER_ID_PARAM = "userId"

@Composable
fun UserDataScreen(viewModel: UserDataViewModel = koinViewModel()) {
    val userData = viewModel.userData.collectAsState(initial = null)
    when (val data = userData.value) {
        null -> Unit
        else -> UserData(data)
    }

}

@Composable
private fun UserData(userData: UserData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = userData.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(86.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row {
            Text(text = userData.firstName)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = userData.lastName)
        }
        Text(text = userData.email)
    }
}