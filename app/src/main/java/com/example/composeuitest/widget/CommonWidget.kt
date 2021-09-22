package com.example.composeuitest.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.composeuitest.R
import com.example.composeuitest.base.InsetAwareTopAppBar

@Composable
fun CommonTopBar(
    title: String = "",
    onClickEvent: () -> Unit,
    iconPainter: Painter = painterResource(id = R.drawable.ic_location),
    iconContentDesc :String?=null
) {
    InsetAwareTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onClickEvent) {
                Icon(
                    painter = iconPainter,
                    contentDescription = iconContentDesc
                )
            }
        }
    )
}