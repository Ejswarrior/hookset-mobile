package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

public class HooksetButton(val modifier: Modifier) {

    @Composable
    fun button(
        variant: String,
        buttonText: String,
        onButtonClick: () -> Unit
    ) {
        Button(modifier = modifier.width(200.dp).background(color = if(variant === "primary") Color.Blue else Color.White ), onClick = onButtonClick) {
            Text(text = buttonText, color = if(variant === "primary") Color.White else Color.Black)
        }
    }

}