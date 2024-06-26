package org.hisp.dhis.common.screens.basicTextInputs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.Description
import org.hisp.dhis.mobile.ui.designsystem.component.InputNumber
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.component.internal.RegExValidations
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputNumberScreen() {
    ColumnComponentContainer {
        Title("Input Number component", textColor = TextColor.OnSurfaceVariant)
        SubTitle("Basic Input Number", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }
        Description("British decimal notation", textColor = TextColor.OnSurfaceVariant)
        InputNumber(
            title = "Label",
            inputTextFieldValue = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
            notation = RegExValidations.BRITISH_DECIMAL_NOTATION,
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        Description("European decimal notation", textColor = TextColor.OnSurfaceVariant)
        var inputValueEuropean by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        InputNumber(
            title = "Label",
            inputTextFieldValue = inputValueEuropean,
            onValueChanged = {
                if (it != null) {
                    inputValueEuropean = it
                }
            },
            notation = RegExValidations.EUROPEAN_DECIMAL_NOTATION,
            state = InputShellState.UNFOCUSED,
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue6 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

        SubTitle("Disabled Input Number ", textColor = TextColor.OnSurfaceVariant)
        InputNumber(
            title = "Label",
            inputTextFieldValue = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )
        Spacer(Modifier.size(Spacing.Spacing18))

        var inputValue7 by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("86")) }

        SubTitle("Disabled Input Number with content ", textColor = TextColor.OnSurfaceVariant)
        InputNumber(
            title = "Label",
            inputTextFieldValue = inputValue7,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
        )
    }
}
