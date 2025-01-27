package ovh.motylek.meowintegrity.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ovh.motylek.meowintegrity.ui.screens.About
import ovh.motylek.meowintegrity.ui.screens.AboutScreen
import ovh.motylek.meowintegrity.ui.screens.Start
import ovh.motylek.meowintegrity.ui.screens.StartScreen
import kotlin.reflect.KClass

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeowNavigation() {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf<Any?>(Start) }
    val navbarItems = listOf(
        NavbarItem(
            route = Start::class,
            title = stringResource(R.string.start),
            icon = Icons.Default.Dashboard
        ),
        NavbarItem(
            route = About::class,
            title = stringResource(R.string.about),
            icon = Icons.Default.Info
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("MeowIntegrity") },
            )
        },
        bottomBar = {
            NavigationBar {
                navbarItems.forEach {
                    val selected = it.route == currentScreen!!::class
                    NavigationBarItem(
                        selected = selected,
                        icon = { Icon(imageVector = it.icon, contentDescription = it.title) },
                        label = { Text(it.title) },
                        onClick = {
                            if (it.route == currentScreen!!::class) return@NavigationBarItem
                            navController.navigate(
                                when (it.route) {
                                    Start::class -> Start()
                                    About::class -> About()
                                    else -> Start()
                                }
                            )
                        }
                    )
                }
            }
        }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = Start(),
            modifier = Modifier.padding(inner)
        ) {
            composable<Start> {
                currentScreen = it.toRoute<Start>()
                StartScreen()
            }
            composable<About> {
                currentScreen = it.toRoute<About>()
                AboutScreen()
            }
        }
    }

}

class NavbarItem(
    val route: KClass<*>,
    val title: String,
    val icon: ImageVector
)