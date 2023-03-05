package com.gen.userdataapp.presentation.ui.userslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.gen.userdataapp.domain.models.UserData
import com.gen.userdataapp.presentation.LocalNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private const val DELAY = 500L

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersScreen(viewModel: UsersViewModel = koinViewModel()) {
    var onlyFavorites by remember {
        mutableStateOf(false)
    }
    val users = viewModel.getUsers().collectAsLazyPagingItems()
    var refreshing by remember {
        mutableStateOf(false)
    }
    val refreshScope = rememberCoroutineScope()
    fun refresh() = refreshScope.launch {
        refreshing = true
        users.refresh()
        delay(DELAY)
        refreshing = false
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = ::refresh
    )
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)
        .pullRefresh(pullRefreshState), topBar = {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Main screen", modifier = Modifier.align(Alignment.Center))
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = onlyFavorites, onCheckedChange = {
                    viewModel.updateOnlyFavorites(it)
                    users.refresh()
                    onlyFavorites = it
                })
                Text(text = "Only favorites")
            }
        }
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            PullRefreshIndicator(
                refreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter),
                scale = true
            )
            LazyColumn(
                Modifier.fillMaxSize(),
                content = {
                    if (!refreshing) {
                        items(users, key = { userData -> userData.email }) { item ->
                            UserItem(item) { userData ->
                                viewModel.updateFavoriteUser(userData)
                            }
                        }
                    }
                })
        }
    }
}

@Composable
private fun UserItem(
    item: UserData?,
    updateFavoriteUser: (UserData) -> Unit
) {
    val navController = LocalNavController.current

    Row(modifier = Modifier
        .background(MaterialTheme.colors.primaryVariant)
        .fillMaxWidth()
        .clickable {
            navController.navigate("details/${item?.id}")
        }) {
        AsyncImage(
            model = item?.avatar,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = item?.firstName.orEmpty())
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = item?.lastName.orEmpty())
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = item?.isFavorite ?: false,
            onCheckedChange = { checked ->
                item?.copy(isFavorite = checked)?.let { userData ->
                    updateFavoriteUser(userData)
                }
            })
        Text(text = "Is favorite")
    }
}