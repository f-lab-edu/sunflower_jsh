package com.example.sunflower

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.sunflower.data.PlantViewData

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
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Crop,
                model = plantViewData.imageUrl,
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
                    onClick = onClickAddButton,
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
                .align(Alignment.CenterHorizontally)
                .padding(
                    vertical = 24.dp,
                ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(R.string.watering_needs),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        val dayToPlural = PluralConverter.dayToPlural(plantViewData.wateringCycle.toInt())

        Text(
            text = stringResource(id = R.string.water) + " " + dayToPlural,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = plantViewData.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        val uriHandler = LocalUriHandler.current
        val hyperlinkString = hyperlinkString(
            plantViewData.plantName,
            LocalContext.current,
            MaterialTheme.colorScheme,
        )

        ClickableText(
            text = hyperlinkString,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                top = 32.dp,
                bottom = 64.dp,
            ),
        ) {
            hyperlinkString
                .getStringAnnotations(TAG, it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    }
}

fun hyperlinkString(
    plantName: String,
    context: Context,
    colorScheme: ColorScheme,
): AnnotatedString = buildAnnotatedString {
    val hyperlinkText = context.getString(R.string.source_text)
    val linkStartIndex = hyperlinkText.indexOf(UNDERLINED_LINK)
    val linkEndIndex = linkStartIndex + UNDERLINED_LINK.length - 1

    append(hyperlinkText)

    addStyle(
        style = SpanStyle(
            color = colorScheme.onBackground,
        ),
        start = 0,
        end = hyperlinkText.length,
    )

    addStyle(
        style = SpanStyle(
            color = colorScheme.primary,
            textDecoration = TextDecoration.Underline,
        ),
        start = linkStartIndex,
        end = linkEndIndex + 1,
    )

    addStringAnnotation(
        tag = TAG,
        annotation = context.getString(R.string.source_link, plantName),
        start = linkStartIndex,
        end = linkEndIndex,
    )
}

private const val UNDERLINED_LINK = "Wikipedia"
private const val TAG = "URL"
