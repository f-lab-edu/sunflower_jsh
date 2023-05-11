package com.example.sunflower

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sunflower.data.PlantViewData

@ExperimentalMaterial3Api
@Composable
fun DetailInfoScreen(
    plantViewData: PlantViewData,
    onClickAddButton: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
            ),
    ) {
        DetailContentView(
            onClickAddButton = onClickAddButton,
            plantViewData = plantViewData,
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .size(48.dp),
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
                .padding(8.dp)
                .size(48.dp),
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
fun DetailContentView(
    plantViewData: PlantViewData,
    onClickAddButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        ConstraintLayout {
            val (
                image, addButton, text,
            ) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    id = plantViewData.imageResId,
                ),
                contentDescription = null,
            )
            if (!plantViewData.isPlanted) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(8.dp)
                        .constrainAs(addButton) {
                            top.linkTo(image.bottom)
                            bottom.linkTo(text.top)
                            end.linkTo(parent.end)
                        },
                    onClick =
                    { onClickAddButton.invoke() },
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 28.dp,
                        bottomStart = 28.dp,
                        bottomEnd = 0.dp,
                    ),
                    containerColor = MaterialTheme.colorScheme.secondary,
                    content = {
                        Icon(
                            imageVector = ImageVector
                                .vectorResource(
                                    id = R.drawable.ic_add,
                                ),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                    },
                )
            }
            DetailTextView(
                plantViewData,
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 12.dp,
                    )
                    .constrainAs(text) {
                        top.linkTo(image.bottom)
                    },
            )
        }
    }
}

@Composable
fun DetailTextView(plantViewData: PlantViewData, modifier: Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = plantViewData.plantName,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(
                    vertical = 24.dp,
                ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.watering_needs),
            modifier = Modifier
                .align(CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Text(
            text = "water ${plantViewData.wateringCycle} days",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = plantViewData.explanation,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = plantViewData.sourceLink,
            modifier = Modifier.padding(
                bottom = 64.dp,
            ),
        )
    }
}
