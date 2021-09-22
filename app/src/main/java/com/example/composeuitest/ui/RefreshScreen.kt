package com.example.composeuitest.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeuitest.R.string
import com.example.composeuitest.base.UiState
import com.example.composeuitest.snack.SnackCollection
import com.example.composeuitest.snack.SnackRepositoryImp
import com.example.composeuitest.util.produceUiState
import com.example.composeuitest.widget.CommonTopBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun RefreshScreen(navController: NavController) {
    /**
     *
     */
    val (postUiState, refreshPost, clearError) = produceUiState(SnackRepositoryImp()) {
        getSnacks()
    }
    Scaffold(
        topBar = {
            CommonTopBar(onClickEvent = {
                navController.navigateUp()
            })
        }
    ) { innerPadding ->

        LoadingContent(
            empty = postUiState.value.initialLoad,
            emptyContent = { FullScreenLoading() },
            loading = postUiState.value.loading,
            onRefresh = {},
            content = {
                RefreshListScreen(
                    data = postUiState.value,
                    onRefresh = refreshPost,
                    onErrorDismiss = clearError
                )
            }
        )
    }

}


@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loading: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (empty) {
        emptyContent()
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = loading),
            onRefresh = onRefresh,
            content = content
        )
    }
}


@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun RefreshListScreen(
    data: UiState<List<SnackCollection>>,
    onRefresh: () -> Unit,
    onErrorDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    if (data.data != null) {
        SnackList(data.data)
    } else if (!data.hasError) {
        // if there are no posts, and no error, let the user refresh manually
        TextButton(onClick = onRefresh, modifier.fillMaxSize()) {
            Text(stringResource(id = string.home_tap_to_load_content), textAlign = TextAlign.Center)
        }
    } else {
        // there's currently an error showing, don't show any content
        Box(modifier.fillMaxSize()) { /* empty screen */ }
    }
}

@Composable
fun SnackList(list: List<SnackCollection>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            list.forEach { snackCollection ->
                Text(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(), text = "Item in ScrollableColumn #${snackCollection.name}"
                )
            }
        }
    }
}