package lolstrategy.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(component: SettingsComponent) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(" SettingsScreen")

        Button(
            onClick = {
            }) {
            Text("Go to  SettingsScreen")
        }
    }
}
