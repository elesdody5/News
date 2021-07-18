package com.news.home.componet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.news.data.entity.Article
import com.news.utils.loadPicture

@Composable
fun NewsCard(
    article: Article,
    onClick: (String) -> Unit,
    addFavourite: (Article) -> Unit,
    removeFavourite: (Article) -> Unit,
) {
    val isFavourite = remember { mutableStateOf(article.favourite) }
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke(article.url ?: "") }),
        elevation = 8.dp,
    ) {

        Column {
            article.urlToImage?.let { url ->
                val image = loadPicture(url = url).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Row {
                Text(
                    text = article.title,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.h6
                )
                IconButton(
                    onClick = {
                        if (isFavourite.value == true) {
                            removeFavourite.invoke(article)
                            isFavourite.value = false
                        } else {
                            addFavourite.invoke(article)
                            isFavourite.value = true
                        }

                    },

                    ) {
                    Icon(
                        if (isFavourite.value == true) Icons.Filled.Star else Icons.Default.Star,
                        "star",
                        tint = if (isFavourite.value == true) Color.Companion.Yellow else Color.Gray,
                    )
                }
            }

            val des = article.description.toString()
            Text(
                text = des,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                style = MaterialTheme.typography.h6,
                fontSize = 14.sp
            )
        }


    }
}