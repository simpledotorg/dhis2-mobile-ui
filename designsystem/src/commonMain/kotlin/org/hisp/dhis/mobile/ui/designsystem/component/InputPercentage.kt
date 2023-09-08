package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing

/**
 * DHIS2 Input percentage. Wraps DHIS · [InputShell].
 * Only integers allowed
 * @param title controls the text to be shown for the title
 * @param state Manages the InputShell state
 * @param supportingText is a list of SupportingTextData that
 * manages all the messages to be shown
 * @param legendData manages the legendComponent
 * @param inputText manages the value of the text in the input field
 * @param isRequiredField controls whether the field is mandatory or not
 * @param onNextClicked gives access to the imeAction event
 * @param onValueChanged gives access to the onValueChanged event
 * @param imeAction controls the imeAction button to be shown
 * @param modifier allows a modifier to be passed externally
 */
@Composable
fun InputPercentage(
    title: String,
    state: InputShellState = InputShellState.UNFOCUSED,
    supportingText: List<SupportingTextData>? = null,
    legendData: LegendData? = null,
    inputText: String? = null,
    isRequiredField: Boolean = false,
    onNextClicked: (() -> Unit)? = null,
    onValueChanged: ((String?) -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier,
) {
    val inputValue by remember(inputText) { mutableStateOf(inputText) }

    var deleteButtonIsVisible by remember { mutableStateOf(!inputText.isNullOrEmpty() && state != InputShellState.DISABLED) }
    val focusManager = LocalFocusManager.current
    val pattern = remember { Regex("^([1-9]|[1-9][0-9]|100)\$") }
    val keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = KeyboardType.Number)
    InputShell(
        modifier = modifier.testTag("INPUT_PERCENTAGE"),
        isRequiredField = isRequiredField,
        title = title,
        primaryButton = {
            if (deleteButtonIsVisible) {
                IconButton(
                    modifier = Modifier.testTag("INPUT_PERCENTAGE_RESET_BUTTON"),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Icon Button",
                        )
                    },
                    onClick = {
                        onValueChanged?.invoke("")
                        deleteButtonIsVisible = false
                    },
                    enabled = state != InputShellState.DISABLED,
                )
            }
        },
        state = state,
        legend = {
            legendData?.let {
                Legend(legendData, Modifier.testTag("INPUT_PERCENTAGE_LEGEND"))
            }
        },
        supportingText = {
            supportingText?.forEach {
                    label ->
                SupportingText(
                    label.text,
                    label.state,
                    modifier = Modifier.testTag("INPUT_PERCENTAGE_SUPPORTING_TEXT").padding(
                        start = Spacing.Spacing16,
                        top = Spacing.Spacing4,
                        end = Spacing.Spacing16,
                    ),
                )
            }
        },
        inputField = {
            BasicInput(
                modifier = Modifier.testTag("INPUT_PERCENTAGE_FIELD"),
                helper = "%",
                helperStyle = InputStyle.WITH_HELPER_AFTER,
                inputText = inputValue ?: "",
                onInputChanged = {
                    if (it.matches(pattern) || it.isEmpty()) {
                        onValueChanged?.invoke(it)
                        deleteButtonIsVisible = it.isNotEmpty()
                    }
                },
                enabled = state != InputShellState.DISABLED,
                state = state,
                keyboardOptions = keyboardOptions,
                onNextClicked = {
                    if (onNextClicked != null) {
                        onNextClicked.invoke()
                    } else {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                },
            )
        },
    )
}