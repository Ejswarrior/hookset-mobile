package ui.components.HooksetInput

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


public class HooksetInput (val modifier: Modifier) {



    fun onChangeInput(inputValue: String, newStringValue: String): String {
        Log.d("input", inputValue)
        Log.d("input", newStringValue)
        val newString: String = newStringValue
        return newString
    }


    @Composable
    fun HooktsetTextField(labelText: String? = null) {
        var inputValue by  remember { mutableStateOf("")}
        Column(modifier.padding(bottom = 24.dp)) {
            if(labelText != null) {
                Text(modifier = modifier.padding(bottom = 6.dp), text = labelText)
            }
            TextField(value = inputValue, onValueChange = {it: String -> inputValue = onChangeInput(inputValue, it)}, modifier = modifier, maxLines = 1, minLines = 1)
        }
    }


}