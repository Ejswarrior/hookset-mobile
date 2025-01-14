package ui.components.HooksetInput

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
   public fun HooksetInput(
        modifier: Modifier,
        labelText: String? = null,
        inputValue: String,
        onChange: (String) -> Unit,
        hidden: Boolean,
        multiLined: Int? = null,
        minLines: Int? = null,
        numInput: Boolean? = false,
   ) {
        Column(modifier.padding(bottom = 24.dp)) {
            if(labelText != null) {
                Text(modifier = modifier.padding(bottom = 6.dp), text = labelText, fontSize = 16.sp)
            }
            TextField(
                value = inputValue,
                onValueChange = {onChange(it)},
                modifier = modifier
                    .fillMaxWidth()
                    .border(BorderStroke(0.dp, Color(Color.White.value))),
                shape = RoundedCornerShape(20),
                maxLines = if(multiLined === null) 1 else multiLined,
                minLines = if(minLines === null) 1 else minLines,
                visualTransformation = if(!hidden) VisualTransformation.None  else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.LightGray, focusedContainerColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Transparent),
            )
        }
    }

