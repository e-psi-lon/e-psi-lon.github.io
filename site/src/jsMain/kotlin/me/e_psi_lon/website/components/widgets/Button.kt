package me.e_psi_lon.website.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toModifier


val buttonBarStyle = CssStyle {
    Modifier.apply {
        
    }
}

fun ButtonBar(content: @Composable () -> Unit) {
    Box(buttonBarStyle.toModifier()) {
        content()
    }
}
