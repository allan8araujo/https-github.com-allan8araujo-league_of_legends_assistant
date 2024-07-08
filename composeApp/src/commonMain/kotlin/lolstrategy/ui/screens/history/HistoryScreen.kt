package lolstrategy.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HistoryScreen(component: HistoryComponent) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Screen History")

        Button(
            onClick = {
            }) {
            Text("Go to Screen History")
        }
    }
}
