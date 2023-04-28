package com.example.sunflower

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Tab
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

enum class TabMenu(val title: String, val icon: Int) {
    MY_GARDEN("My Garden", R.drawable.ic_flower),
    PLANT_LIST("Plant list", R.drawable.ic_grass),
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.background,
    )


    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.primary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
        ) {
            TabMenu.values().forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    icon = {
                        Icon(imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = null)
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    text = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                )
            }
        }
        HorizontalPager(
            count = TabMenu.values().size,
            state = pagerState,
        ) {
            when (TabMenu.values()[pagerState.currentPage]) {
                TabMenu.MY_GARDEN -> MyGardenScreen()
                TabMenu.PLANT_LIST -> PlantListScreen()
            }
        }
    }
}
