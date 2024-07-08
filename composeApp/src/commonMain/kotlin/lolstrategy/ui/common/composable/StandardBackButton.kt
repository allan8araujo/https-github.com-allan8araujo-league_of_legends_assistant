package lolstrategy.ui.common.composable

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun StandardBackButton(
    modifier: Modifier = Modifier,
    goBack: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = {
            goBack()
        }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back"
        )
    }
}
