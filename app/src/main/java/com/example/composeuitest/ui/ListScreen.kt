package com.example.composeuitest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeuitest.widget.CommonTopBar
import com.google.accompanist.insets.statusBarsPadding



@Composable
fun ListScreen(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column {
            CommonTopBar("ListScreen", onClickEvent=upPress)
            LazyColumn(modifier = Modifier.statusBarsPadding()){
                items(60){index ->
                    Text(modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),text = "Item in ScrollableColumn #$index")
                }
            }
        }


    }
}


