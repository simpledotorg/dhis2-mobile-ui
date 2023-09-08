package org.hisp.dhis.common.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.InputLetter
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState
import org.hisp.dhis.mobile.ui.designsystem.component.LegendData
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextData
import org.hisp.dhis.mobile.ui.designsystem.component.SupportingTextState
import org.hisp.dhis.mobile.ui.designsystem.component.Title
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun InputLetterScreen() {
    ColumnComponentContainer {
        Title("Input Letter component", textColor = TextColor.OnSurfaceVariant)
        SubTitle(" Basic Input Letter", textColor = TextColor.OnSurfaceVariant)
        var inputValue1 by rememberSaveable { mutableStateOf("") }

        InputLetter(
            title = "Label",
            inputText = inputValue1,
            onValueChanged = {
                if (it != null) {
                    inputValue1 = it
                }
            },
        )
        SubTitle(" Basic Input Letter with erro", textColor = TextColor.OnSurfaceVariant)
        var inputValueError by rememberSaveable { mutableStateOf("") }

        InputLetter(
            title = "Label",
            inputText = inputValueError,
            onValueChanged = {
                if (it != null) {
                    inputValueError = it
                }
            },
            supportingText = listOf(SupportingTextData("Letters only. eg. A, B, C", SupportingTextState.ERROR)),
            state = InputShellState.ERROR,
        )
        var inputValue2 by rememberSaveable { mutableStateOf("") }
        SubTitle("Input Letter with legend", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue2,
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue2 = it
                }
            },
        )

        var inputValue3 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input Letter with Supporting text", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue3,
            supportingText = listOf(SupportingTextData("Supporting text", SupportingTextState.DEFAULT)),
            onValueChanged = {
                if (it != null) {
                    inputValue3 = it
                }
            },
        )

        var inputValue4 by rememberSaveable { mutableStateOf("") }

        SubTitle("Input Letter with Supporting text and legend", textColor = TextColor.OnSurfaceVariant)

        InputLetter(
            title = "Label",
            inputText = inputValue4,
            supportingText = listOf(
                SupportingTextData(
                    "Supporting text",
                    SupportingTextState.DEFAULT,
                ),
            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            onValueChanged = {
                if (it != null) {
                    inputValue4 = it
                }
            },
        )
        SubTitle("Input Letter with error and warning text and legend", textColor = TextColor.OnSurfaceVariant)
        var inputValue5 by rememberSaveable { mutableStateOf("") }

        InputLetter(
            title = "Label",
            inputText = inputValue5,
            supportingText = listOf(
                SupportingTextData("Supporting text", SupportingTextState.DEFAULT),
                SupportingTextData("Supporting text", SupportingTextState.WARNING),
                SupportingTextData("Supporting text", SupportingTextState.ERROR),

            ),
            legendData = LegendData(SurfaceColor.CustomGreen, "Legend"),
            state = InputShellState.ERROR,
            imeAction = ImeAction.Done,
            onValueChanged = {
                if (it != null) {
                    inputValue5 = it
                }
            },
        )
        var inputValue6 by rememberSaveable { mutableStateOf("") }

        SubTitle("Disabled Input Letter ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue6,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue6 = it
                }
            },
        )

        var inputValue7 by rememberSaveable { mutableStateOf("A") }

        SubTitle("Disabled Input Letter with content ", textColor = TextColor.OnSurfaceVariant)
        InputLetter(
            title = "Label",
            inputText = inputValue7,
            state = InputShellState.DISABLED,
            onValueChanged = {
                if (it != null) {
                    inputValue7 = it
                }
            },
        )
    }
}