package org.hisp.dhis.common.screens.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.hisp.dhis.mobileui.designsystem.component.CheckBox

@Composable
internal fun CheckboxPreview(checked: MutableState<Boolean>, enabled: Boolean = true) {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = null
    )
}

@Composable
internal fun TextCheckboxPreview(checked: MutableState<Boolean>, enabled: Boolean = true, text: String = "Option") {
    CheckBox(
        checked = checked,
        enabled = enabled,
        textInput = text,
    )
}
