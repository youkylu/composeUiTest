package com.example.composeuitest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeuitest.base.ROUTE

@Composable
fun HomeScreen(navController: NavController) {

    Column (
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),

    ){
        Button(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(ROUTE.STATUS_BAR_ROUTE) }) {
            Text(text = "statusBar")
        }
//        Spacer(Modifier.size(10.dp))
        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.TAB_LAYOUT) }) {
            Text(text = "tabLayout")
        }
//        Spacer(Modifier.size(10.dp))
        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.LIST_LAYOUT) }) {
            Text(text = "listLayout")
        }

        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.REFRESH_LAYOUT) }) {
            Text(text = "refreshLayout")
        }

        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.SELF_DEFINE_LAYOUT) }) {
            Text(text = "selfDefine")
        }

        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.ANIMATION_LAYOUT) }) {
            Text(text = "animationLayout")
        }
        Button(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(ROUTE.GESTURES_LAYOUT) }) {
            Text(text = "gestureLayout")
        }

    }

}