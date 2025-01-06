package utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class ScreenSize (
    val height: Dp,
    val width: Dp,
)
@Composable
fun GetScreenSize(): ScreenSize {
    val context = LocalConfiguration.current

    // Width and height of screen
    val width = context.screenWidthDp.dp
    val height = context.screenHeightDp.dp


    return ScreenSize(height,width)
}

