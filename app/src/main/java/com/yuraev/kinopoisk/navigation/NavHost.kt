package com.yuraev.kinopoisk.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.serialization.Serializable
import com.yuraev.kinopoiskmain.DocsUI
import com.yuraev.kinopoiskmain.favorite.FavoriteKinopoisk
import com.yuraev.kinopoiskmain.network.NetworkKinopoisk
import androidx.compose.runtime.getValue
import androidx.navigation.toRoute
import com.yuraev.kinopoiskmain.fulldoc.FullDoc




@Serializable
object Network

@Serializable
object Favorite

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)
val topLevelRoutes = listOf(
    TopLevelRoute("Network", Network, Icons.Default.Home),
    TopLevelRoute("Favorite", Favorite, Icons.Default.Favorite),
)

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Network) {
        composable<Network> {
            NetworkKinopoisk(modifier = modifier,goToDetail = {doc -> navController.navigate((doc))})
        }
        composable<Favorite> {
            FavoriteKinopoisk(modifier = modifier, goToDetail = {doc -> navController.navigate(doc)})
        }
        composable<DocsUI> { backStackEntry ->
            val doc: DocsUI = backStackEntry.toRoute()
            FullDoc(doc)
        }

    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(modifier = modifier){
        topLevelRoutes.forEach { topLevelRoute ->
            NavigationBarItem(
                selected = currentDestination?.route == topLevelRoute.route::class.qualifiedName,
                onClick = { navController.navigate(topLevelRoute.route) },
                icon = {Icon(topLevelRoute.icon, contentDescription = null)}
            )
        }
    }
}
