package com.clone.metabox.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.*

@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:

                return@derivedStateOf true

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if(it) loadMore()
            }
    }
}

@Composable
fun LazyGridState.OnBottomReached (
    loadMore: () -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:

                return@derivedStateOf true

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if(it) loadMore()
            }
    }
}