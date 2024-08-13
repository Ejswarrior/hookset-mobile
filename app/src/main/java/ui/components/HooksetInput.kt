package ui.components.HooksetInput

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


public class HooksetInput (val modifier: Modifier) {

    var inputValue: MutableState<String> = remember { mutableStateOf("")}
        private set

    fun onChangeInput(newStringValue: String) {
        Log.d("input", inputValue)
        inputValue += newStringValue
    }

    @Composable
    fun HooktsetTextField() {
        Row {
            TextField(value = inputValue, onValueChange = ::onChangeInput, modifier = modifier)
        }
    }
}