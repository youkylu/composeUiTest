package com.example.composeuitest.base

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.composeuitest.R
import com.example.composeuitest.base.ROUTE.ANIMATION_LAYOUT
import com.example.composeuitest.base.ROUTE.GESTURES_LAYOUT
import com.example.composeuitest.base.ROUTE.HOME_ROUTE
import com.example.composeuitest.base.ROUTE.LIST_LAYOUT
import com.example.composeuitest.base.ROUTE.REFRESH_LAYOUT
import com.example.composeuitest.base.ROUTE.SELF_DEFINE_LAYOUT
import com.example.composeuitest.base.ROUTE.STATUS_BAR_ROUTE
import com.example.composeuitest.base.ROUTE.TAB_LAYOUT
import com.example.composeuitest.tab.TabScreen
import com.example.composeuitest.ui.*


@Composable
fun navController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstScreen"
    ) {
        composable("FirstScreen") {
            FirstScreen()
        }
        composable("SecondScreen") {
            SecondScreen()
        }
    }

}


@ExperimentalAnimationApi
@Composable
fun BottomTab(
) {
    val listItem = listOf(
        stringResource(R.string.home),
        stringResource(R.string.find),
        stringResource(R.string.profile)
    )
    val icon = listOf(R.drawable.ic_home, R.drawable.ic_menu, R.drawable.ic_person)
    val materialIcon = listOf(Icons.Outlined.Home, Icons.Outlined.Search, Icons.Outlined.Person)

    val selectIndex = remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    Scaffold(
        //        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val sections = remember { NavScreen.values() }
            val routes = remember { sections.map { it.route } }
            if (currentRoute in routes) {
                BottomNavigation(backgroundColor = Color.White, elevation = 3.dp) {

                    sections.forEachIndexed { index, navScreen ->
                        val selected = index == selectIndex.value
                        val tint by animateColorAsState(
                            if (selected) {
                                Color(0xFF0077E6)
                            } else {
                                Color(0xFF000000)
                            }
                        )
                        BottomNavigationItem(
                            selected = currentRoute == navScreen.route,
                            onClick = {
                                selectIndex.value = index
                                navController.navigate(navScreen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                /**
                                 * if use resource, painter will useful,
                                 * else imageVector or ImageBitmap should be used
                                 */
                                //                            Icon(
                                //                                painter = painterResource(id = icon[index]),
                                //                                tint = tint,
                                //                                contentDescription = null
                                //                            )
                                Icon(
                                    imageVector = materialIcon[index],
                                    tint = tint,
                                    contentDescription = null
                                )

                            },
                            label = {
                                Text(
                                    text = listItem[index],
                                    textAlign = TextAlign.Center,
                                    color = tint
                                )
                            }
                        )

                    }
                }
            }
        },)
    {
        NavHost(
            navController = navController,
            startDestination = HOME_ROUTE
        ) {
            navigation(route = HOME_ROUTE, startDestination = NavScreen.Home.route)
            {
                composable(NavScreen.Home.route) {
                    HomeScreen(navController)
                }
                composable(NavScreen.Find.route) {
                    Find(navController)
                }
                composable(NavScreen.Profile.route) {
                    Profile(navController)
                }
            }
            composable(route = STATUS_BAR_ROUTE) {
                BoxScreen()
            }
            composable(route = TAB_LAYOUT) {
                TabScreen()
            }
            composable(route = LIST_LAYOUT) {
                ListScreen(navController = navController)
            }
            composable(route = REFRESH_LAYOUT) {
                RefreshScreen(navController = navController)
            }
            composable(route = SELF_DEFINE_LAYOUT) {
                SelfDefineScreen()
            }
            composable(route= ANIMATION_LAYOUT){
                AnimationScreen()
            }
            composable(route= GESTURES_LAYOUT){
                GesturesScreen()
            }
        }
    }
}


@Composable
fun Find(navController: NavController) {
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context, "find", Toast.LENGTH_SHORT).show() }) {
        Text(text = "find")
    }
}

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context, "profile", Toast.LENGTH_SHORT).show() }) {
        Text(text = "profile")
    }
}