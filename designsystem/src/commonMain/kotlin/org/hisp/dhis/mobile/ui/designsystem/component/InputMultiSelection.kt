package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.hisp.dhis.mobile.ui.designsystem.resource.provideStringResource
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor

private const val INLINE_CHECKBOXES_MIN_REQ_ITEMS = 6
private const val MAX_CHECKBOXES_ITEMS_TO_SHOW = 50

/**
 * DHIS2 input multi selection component.
 * wraps DHIS2 [InputShell].
 * @param items: list of [CheckBoxData] for selectable items.
 * @param title: label of the component.
 * @param state: [InputShellState].
 * @param inputStyle: manages the InputShell style.
 * @param supportingTextData: list of [SupportingTextData] that manages all the messages to be shown.
 * @param legendData: [LegendData] to be render below the input shell.
 * @param isRequired: mark this input as required.
 * @param onItemsSelected: callback to items are selected.
 * @param modifier: optional modifier.
 * @param noResultsFoundString: text to be shown in pop up when no results are found.
 * @param doneButtonText: text to be shown for accept button in pop up.
 * @param onClearItemSelection: callback for clear item selection.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputMultiSelection(
    items: List<CheckBoxData>,
    title: String,
    state: InputShellState,
    supportingTextData: List<SupportingTextData>?,
    legendData: LegendData?,
    isRequired: Boolean,
    onItemsSelected: (List<CheckBoxData>) -> Unit,
    modifier: Modifier = Modifier,
    noResultsFoundString: String = provideStringResource("no_results_found"),
    searchToFindMoreString: String = provideStringResource("search_to_see_more"),
    doneButtonText: String = provideStringResource("done"),
    inputStyle: InputStyle = InputStyle.DataInputStyle(),
    onClearItemSelection: () -> Unit,
) {
    var showMultiSelectBottomSheet by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val clearSelectionButton: (@Composable () -> Unit)? =
        if (items.any { it.checked } && state != InputShellState.DISABLED) {
            {
                IconButton(
                    modifier = Modifier.testTag("INPUT_MULTI_SELECT_CLEAR_ICON_BUTTON")
                        .padding(Spacing.Spacing0),
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = "Clear Selection",
                        )
                    },
                    onClick = {
                        focusRequester.requestFocus()
                        onClearItemSelection()
                    },
                )
            }
        } else {
            null
        }

    val bottomSheetDropdownButton: (@Composable () -> Unit) = {
        IconButton(
            modifier = Modifier.testTag("INPUT_MULTI_SELECT_DROP_DOWN_ICON_BUTTON")
                .padding(Spacing.Spacing0),
            enabled = state != InputShellState.DISABLED,
            icon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Show MultiSelect BottomSheet",
                )
            },
            onClick = {
                focusRequester.requestFocus()
                showMultiSelectBottomSheet = true
            },
        )
    }

    Box {
        InputShell(
            title = title,
            state = state,
            modifier = modifier.testTag("INPUT_MULTI_SELECT").focusRequester(focusRequester),
            inputStyle = inputStyle,
            supportingText = supportingTextData?.let {
                {
                    it.forEach { supportTextData ->
                        SupportingTextData(
                            text = supportTextData.text,
                            state = supportTextData.state,
                        )
                    }
                }
            },
            legend = legendData?.let { legendData ->
                {
                    Legend(legendData = legendData)
                }
            },
            isRequiredField = isRequired,
            inputField = {
                if (items.size <= INLINE_CHECKBOXES_MIN_REQ_ITEMS) {
                    Column(
                        modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST")
                            .offset(x = -Spacing.Spacing8),
                    ) {
                        items.forEachIndexed { index, item ->
                            CheckBox(
                                modifier = Modifier.testTag("INPUT_MULTI_SELECT_CHECKBOX_LIST_ITEM_$index")
                                    .fillMaxWidth(),
                                checkBoxData = item.copy(
                                    enabled = state != InputShellState.DISABLED,
                                ),
                                onCheckedChange = { checked ->
                                    focusRequester.requestFocus()
                                    onItemsSelected(
                                        items.toMutableList().map {
                                            if (it.uid == item.uid) {
                                                it.copy(checked = checked)
                                            } else {
                                                it
                                            }
                                        },
                                    )
                                },
                            )
                        }
                    }
                } else {
                    Box(Modifier.fillMaxWidth().focusable()) {
                        Box(
                            modifier = Modifier
                                .testTag("INPUT_MULTI_SELECT_CLICKABLE")
                                .matchParentSize()
                                .requiredHeightIn(min = Spacing.Spacing48)
                                .alpha(0f)
                                .clickable(
                                    enabled = state != InputShellState.DISABLED &&
                                        items.size > INLINE_CHECKBOXES_MIN_REQ_ITEMS,
                                    onClick = {
                                        focusRequester.requestFocus()
                                        showMultiSelectBottomSheet = true
                                    },
                                ),
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(Spacing.Spacing8),
                        ) {
                            items.forEachIndexed { index, item ->
                                SelectedItemChip(
                                    item = item,
                                    index = index,
                                    focusRequester = focusRequester,
                                    enabled = state != InputShellState.DISABLED,
                                ) { newItem ->
                                    onItemsSelected(
                                        items.map {
                                            when (it.uid) {
                                                newItem.uid -> newItem
                                                else -> it
                                            }
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            },
            primaryButton = {
                Box(Modifier.testTag("INPUT_MULTI_SELECT_ACTION_ICON_BUTTON").requiredSize(48.dp)) {
                    if (items.size < INLINE_CHECKBOXES_MIN_REQ_ITEMS) {
                        clearSelectionButton?.invoke()
                    } else {
                        bottomSheetDropdownButton.invoke()
                    }
                }
            },
        )

        if (showMultiSelectBottomSheet) {
            MultiSelectBottomSheet(
                items = items,
                title = title,
                noResultsFoundString = noResultsFoundString,
                searchToFindMoreString = searchToFindMoreString,
                doneButtonText = doneButtonText,
                onItemsSelected = {
                    onItemsSelected(it)
                    showMultiSelectBottomSheet = false
                },
                onDismiss = {
                    showMultiSelectBottomSheet = false
                },
            )
        }
    }
}

@Composable
private fun SelectedItemChip(
    item: CheckBoxData,
    index: Int,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClearItemSelection: (CheckBoxData) -> Unit,
) {
    if (item.checked) {
        InputChip(
            modifier = modifier
                .testTag("INPUT_MULTI_SELECT_CHECKBOX_CHIP_ITEM_$index"),
            label = item.textInput!!.toString(),
            selected = false,
            enabled = enabled,
            withTrailingIcon = true,
            hasTransparentBackground = true,
            onIconSelected = {
                onClearItemSelection(item.copy(checked = false))
            },
            focusRequester = focusRequester,
        )
    }
}

@Composable
fun MultiSelectBottomSheet(
    items: List<CheckBoxData>,
    title: String,
    noResultsFoundString: String,
    searchToFindMoreString: String,
    doneButtonText: String,
    onItemsSelected: (List<CheckBoxData>) -> Unit,
    onDismiss: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredOptions = items
        .filter { it.textInput!!.contains(searchQuery, ignoreCase = true) }
        .toMutableStateList()

    val itemsModified = remember { items.toMutableList() }

    BottomSheetShell(
        modifier = Modifier.testTag("INPUT_MULTI_SELECT_BOTTOM_SHEET"),
        title = title,
        content = {
            Column(
                modifier = Modifier
                    .padding(top = Spacing.Spacing8),
            ) {
                if (filteredOptions.isNotEmpty()) {
                    filteredOptions
                        .take(MAX_CHECKBOXES_ITEMS_TO_SHOW)
                        .forEachIndexed { index, item ->
                            CheckBox(
                                checkBoxData = item.copy(
                                    textInput = bottomSheetItemLabel(
                                        text = item.textInput!!,
                                        searchQuery = searchQuery,
                                    ),
                                ),
                                onCheckedChange = {
                                    filteredOptions[index] = item.copy(checked = it)
                                    itemsModified[index] = item.copy(checked = it)
                                },
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    if (filteredOptions.size > MAX_CHECKBOXES_ITEMS_TO_SHOW) {
                        Text(
                            text = searchToFindMoreString,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                        )
                    }
                } else {
                    Text(
                        text = noResultsFoundString,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                    )
                }
            }
        },
        onDismiss = onDismiss,
        searchQuery = searchQuery,
        onSearch = { searchQuery = it },
        onSearchQueryChanged = { searchQuery = it },
        buttonBlock = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onItemsSelected(
                        items.map { item ->
                            itemsModified.find { it.uid == item.uid } ?: item
                        },
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                    )
                },
                text = doneButtonText,
                style = ButtonStyle.FILLED,
            )
        },
    )
}

private fun bottomSheetItemLabel(
    text: AnnotatedString,
    searchQuery: String,
): AnnotatedString {
    val label = buildAnnotatedString {
        val highlightIndexStart = text.indexOf(searchQuery, ignoreCase = true)
        val highlightIndexEnd = highlightIndexStart + searchQuery.length

        if (highlightIndexStart >= 0) {
            append(text.substring(0, highlightIndexStart))

            withStyle(
                SpanStyle(background = SurfaceColor.Primary.copy(alpha = 0.1f)),
            ) {
                append(
                    text.substring(
                        startIndex = highlightIndexStart,
                        endIndex = highlightIndexEnd,
                    ),
                )
            }

            append(
                text.substring(
                    startIndex = highlightIndexEnd,
                    endIndex = text.length,
                ),
            )
        } else {
            append(text)
        }
    }

    return label
}
