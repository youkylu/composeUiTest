package com.example.composeuitest.ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.composeuitest.R

enum class NavScreen(val route: String, @StringRes val resourceId:Int) {
     Home ("home/home", R.string.home),

     Find ("home/find", R.string.find),

     Profile ("home/profile",R.string.profile)
}

@Composable
fun FirstScreen(){
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context,"first",Toast.LENGTH_SHORT).show() }) {
        Text(text = "first")
    }
}

@Composable
fun SecondScreen(){
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context,"second",Toast.LENGTH_SHORT).show() }) {
        Text(text = "second")
    }
}

@Composable
fun ThirdScreen(){
    val context = LocalContext.current
    Button(onClick = { Toast.makeText(context,"third",Toast.LENGTH_SHORT).show() }) {
        Text(text = "third")
    }
}