package com.example.composeuitest.ui


import android.text.TextUtils
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Preview
@Composable
fun AnimationScreen() {
    LazyColumn() {
        item { AnimationContentSize() }
        item { AnimationContentSize1() }
        item { CrossfadeLayout() }
        item { AnimatedVisibilityLayout() }
    }
}

@Composable
fun AnimationContentSize() {
    var expand by remember { mutableStateOf(false) }
    val tint by animateColorAsState(
        if (expand) {
            Color(0xFF0077E6)
        } else {
            Color(0xFF000000)
        }
    )

    Column {
        Button(onClick = { expand = !expand }) {
            Text(if (expand) "Shrink" else "Expand")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(tint)
        ) {
            Text(
                text = "animateContentSize() animates its own size when its child modifier (or the child composable if it is already at the tail of the chain) changes size. " +
                        "This allows the parent modifier to observe a smooth size change, resulting in an overall continuous visual change.",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp),
                maxLines = if (expand) Int.MAX_VALUE else 2,
                color = Color.White
            )
        }
    }
}

@Composable
fun AnimationContentSize1() {
    var message by remember { mutableStateOf("Hello") }
    Box(
        modifier = Modifier
            .animateContentSize()
    ) {
        Box {

            Column {
                Text(
                    text = message,
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .background(Color.Green)
                )
                Spacer(Modifier.height(20.dp))
                Button(onClick = {
                    message = if (TextUtils.equals(message, "HelloWorld")) {
                        "Hello"
                    } else {
                        "HelloWorld"
                    }
                }) {
                    Text(text = "点击改变文字长度")
                }
            }

        }
    }
}

enum class AnimationScene {
    Text,
    Icon
}

@Composable
fun CrossfadeLayout() {
    var scene by remember { mutableStateOf(AnimationScene.Text) }

    Column {
        Button(onClick = {
            scene = when (scene) {
                AnimationScene.Text -> AnimationScene.Icon
                AnimationScene.Icon -> AnimationScene.Text
            }
        }) {
            Text(text = "toggle")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Crossfade(targetState = scene, animationSpec = tween(durationMillis = 1000)) { scene ->
            when (scene) {
                AnimationScene.Text ->
                    Text(text = "Phone", fontSize = 32.sp)
                AnimationScene.Icon ->
                    Icon(
                        imageVector = Icons.Default.Phone,
                        null,
                        modifier = Modifier.size(48.dp)
                    )
            }

        }

    }
}

@ExperimentalAnimationApi
@Composable
fun AnimatedVisibilityLayout() {
    var visible by remember {
        mutableStateOf(true)
    }
    Column{
        Button(onClick = { visible = !visible }) {
            Text(text = if(visible) "Hide" else "Show")
        }

        Spacer(Modifier.height(16.dp))

        AnimatedVisibility(visible = visible) {
            Box(modifier = Modifier.size(128.dp).background(Color.Blue)){

            }
        }
    }
}

