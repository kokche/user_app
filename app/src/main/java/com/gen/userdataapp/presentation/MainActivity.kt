package com.gen.userdataapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gen.userdataapp.presentation.ui.theme.UserDataAppTheme
import com.gen.userdataapp.presentation.ui.userinfo.USER_ID_PARAM
import com.gen.userdataapp.presentation.ui.userinfo.UserDataScreen
import com.gen.userdataapp.presentation.ui.userslist.UsersScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            UserDataAppTheme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    NavHost(navController = navController, startDestination = MAIN) {
                        composable(MAIN) {
                            UsersScreen()
                        }
                        composable(
                            route = DETAILS,
                            arguments = listOf(
                                navArgument(USER_ID_PARAM) { type = NavType.IntType }
                            )
                        ) { entry ->
                            val userId = entry.arguments?.getInt(USER_ID_PARAM) ?: 0
                            UserDataScreen(
                                koinViewModel(parameters = { parametersOf(userId) })
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val MAIN = "main"
        private const val DETAILS = "details/{userId}"
    }
}

val LocalNavController = compositionLocalOf<NavController> { error("No user found!") }
