package com.example.sunflower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.TabRow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

enum class TabMenu(val title: String, val icon: Int) {
    MY_GARDEN("My Garden", R.drawable.ic_flower),
    PLANT_LIST("Plant list", R.drawable.ic_grass),
}

@ExperimentalMaterial3Api
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    onNavigateToDetail: () -> Unit,
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        CenterAlignedTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .width(IntrinsicSize.Min),
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            actions = {
                if (TabMenu.values()[pagerState.currentPage] == TabMenu.PLANT_LIST) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                        contentDescription = null,
                    )
                }
            }
        )

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
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.icon),
                            contentDescription = null,
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.inversePrimary,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    text = {
                        Text(
                            text = item.title,
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
                TabMenu.PLANT_LIST -> PlantListScreen(onNavigateToDetail)
            }
        }
    }
}
