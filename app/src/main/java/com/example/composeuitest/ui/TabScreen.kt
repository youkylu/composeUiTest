package com.example.composeuitest.tab

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.composeuitest.base.InsetAwareTopAppBar
import com.example.composeuitest.R
import com.example.composeuitest.base.Red200
import com.example.composeuitest.base.Red700
import com.example.composeuitest.ui.FirstScreen
import com.example.composeuitest.ui.SecondScreen
import com.example.composeuitest.ui.ThirdScreen
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

enum class TabSections(val title: String) {
    Topic("Topic"),
    People("People"),
    Publication("Publication")
}

class TabContent(val section: TabSections, val content: @Composable () -> Unit)


@Composable
fun TabScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .navigationBarsPadding(start = false, end = false, bottom = false)
    ) {
        val (selectedTabIndex , updateIndex)= remember { mutableStateOf(TabSections.Topic.ordinal) }
//       val  topicsSection = TabContent(TabSections.Topic){
//
//       }
//        val tabContent = listOf(topicsSection, peopleSection, publicationSection)
        Column {
            topBar()
            tabContent(selectedTabIndex, updateIndex)
            Box {
                when(selectedTabIndex){
                    0-> FirstScreen()
                    1-> SecondScreen()
                    else-> ThirdScreen()
                }

            }
        }

    }
}


@Composable
fun tabContent(selectedTabIndex:Int,updateIndex: (Int) -> Unit) {

    val titleList = TabSections.values().map { it.title }

    Column {
        TabRow(
            //selectedTabIndex也可以等于TabSections.Topic.ordinal
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Red200,
            indicator = { },
            divider = { }
        ) {
            titleList.forEachIndexed { index, title ->
                val selected = index == selectedTabIndex
                var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 3.dp)
                if (selected) {
                    textModifier =
                        Modifier
                            .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
                            .then(textModifier)
                }
                Tab(
                    selected = selected,
                    onClick = { /**onTabSelected(CraneScreen.values()[index]) **/
                        updateIndex(index)

                    }
                ) {
                    Text(
                        modifier = textModifier,
                        text = title.uppercase(ConfigurationCompat.getLocales(LocalConfiguration.current)[0])
                    )
                }

            }
        }
    }
}


@Composable
fun topBar() {
    val context = LocalContext.current
    InsetAwareTopAppBar(
        title = { Text("TabView") },
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