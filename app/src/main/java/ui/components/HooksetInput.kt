package ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

public class HooksetInput constructor(val modifier: Modifier) {

    var inputValue: String = ""
        private set
        public get

    fun onChangeInput(newStringValue: String) {
        inputValue = newStringValue;
    }

    @Composable
    fun HooktsetTextField() {
        Row {
            TextField(value = inputValue, onValueChange = ::onChangeInput, modifier = modifier)
        }
    }
}