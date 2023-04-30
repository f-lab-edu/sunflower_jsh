package com.example.sunflower

import com.example.sunflower.data.MockUpDataList
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Preview
@Composable
fun DetailInfoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f),
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    id = MockUpDataList.plantList[0].image,
                ),
                contentDescription = null,
            )

            DetailTextContent()

        }

        FloatingActionButton(
            modifier = Modifier
                .padding(8.dp),
            onClick = { /*TODO*/ },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.secondary,
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                )
            },
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            onClick = { /*TODO*/ },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.secondary,
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                )
            },
        )
    }
}

@Composable
fun DetailTextContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "이름",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(vertical = 24.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.wateringNeeds),
            modifier = Modifier
                .align(CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Text(
            text = "water 21 days",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = "설명\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = "링크",
            modifier = Modifier.padding(
                bottom = 64.dp,
            ),
        )
    }
}
