package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun detailRow(title: String, detail: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "$title:", fontSize = 6.sp, color = Color.LightGray)
        Text(text = detail, color = Color.White, fontSize = 8.sp)
    }
}
@Composable
fun PostDetails(bodyOfwaterCaughtIn: String, fishSpecies: String, modifier: Modifier,  weight: Int? = null, length: Int? = null) {
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier.padding(12.dp)) {
        detailRow(title = "Catch Location", detail = bodyOfwaterCaughtIn)
        detailRow(title = "Fish Species", detail = fishSpecies)
        detailRow(title = "Weight", detail = if(weight != null) "$weight lb" else "N/A")
        detailRow(title = "Length", detail = if(length != null) "$length in" else "N/A")
    }
}

@Preview
@Composable
fun PostDetail() {
    PostDetails(bodyOfwaterCaughtIn = "Niagara River", fishSpecies = "Steelhead", Modifier)
}