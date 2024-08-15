package ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
        Button(modifier = modifier
            .width(280.dp)
            .padding(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = if( variant === "primary") Color.Blue else Color.White),
            shape = RoundedCornerShape(25), onClick = onButtonClick) {
                Text(text = buttonText, color = if(variant === "primary") Color.White else Color.Black)

        }
    }

}