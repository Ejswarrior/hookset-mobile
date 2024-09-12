package ui.components.HooksetInput

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

    @Composable
   public fun HooksetInput(modifier: Modifier, labelText: String? = null, inputValue: String, onChange: (String) -> Unit, hidden: Boolean) {
        Column(modifier.padding(bottom = 24.dp)) {
            if(labelText != null) {
                Text(modifier = modifier.padding(bottom = 6.dp), text = labelText)
            }
            TextField(value = inputValue, onValueChange = {onChange(it)}, modifier = modifier, maxLines = 1, minLines = 1,visualTransformation = if(!hidden) VisualTransformation.None  else PasswordVisualTransformation())
        }
    }

