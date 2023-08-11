package org.hisp.dhis.mobileui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobileui.designsystem.theme.Outline
import org.hisp.dhis.mobileui.designsystem.theme.Ripple
import org.hisp.dhis.mobileui.designsystem.theme.Spacing
import org.hisp.dhis.mobileui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobileui.designsystem.theme.TextColor

/**
 * DHIS2 radio button with or without text. Wraps Material 3 [Checkbox].
 *
 * @param checked Controls if the option is checked.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param textInput The checkbox option text.
 * @param onClick Will be called when the user clicks the box or the text.
 */
@Composable
fun CheckBox(
    checked: Boolean,
    enabled: Boolean,
    textInput: String?,
    onClick: (Boolean) -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides Ripple.CustomDHISRippleTheme) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onClick,
                enabled = enabled,
                modifier = Modifier
                    .size(Spacing.Spacing40),
                colors = CheckboxDefaults.colors(
                    checkedColor = SurfaceColor.Primary,
                    uncheckedColor = Outline.Dark,
                    disabledCheckedColor = TextColor.OnDisabledSurface,
                    disabledUncheckedColor = TextColor.OnDisabledSurface
                )
            )
            if (!textInput.isNullOrEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(top = Spacing.Spacing8, bottom = Spacing.Spacing8)
                        .clickable { onClick(!checked) },
                    text = textInput,
                    color = if (enabled) {
                        TextColor.OnSurface
                    } else {
                        TextColor.OnDisabledSurface
                    }
                )
            }
        }
    }
}
