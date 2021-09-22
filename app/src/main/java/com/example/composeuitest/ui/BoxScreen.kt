package com.example.composeuitest.ui


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeuitest.R
import com.example.composeuitest.base.InsetAwareTopAppBar
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Preview
@Composable
fun BoxScreen(
) {
    Box(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding(start = false, end = false, bottom = false)
    ) {
        Header()
        topBar()
        Text(
            text = "ceshi",
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding(start = false, end = false)
                .background(color = Color(0xff009900)),
            color = Color(0xff999999),
        )
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Blue)
    )
}

@Composable
fun topBar() {
    val context = LocalContext.current
    InsetAwareTopAppBar(
        title = { Text("BoxView") },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = stringResource(R.string.click)
                )
            }
        }
    )
}