package org.hisp.dhis.common.screens.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.hisp.dhis.common.screens.NoComponentSelectedScreen
import org.hisp.dhis.mobile.ui.designsystem.component.DropdownItem
import org.hisp.dhis.mobile.ui.designsystem.component.InputDropDown
import org.hisp.dhis.mobile.ui.designsystem.component.InputShellState

@Composable
fun ButtonsScreen() {
    val currentScreen = remember { mutableStateOf(ButtonScreens.BUTTON) }

    val screenDropdownItemList = mutableListOf<DropdownItem>()
    ButtonScreens.entries.forEach {
        if (it != ButtonScreens.NO_COMPONENT_SELECTED) {
            screenDropdownItemList.add(DropdownItem(it.label))
        }
    }
    InputDropDown(
        "Display",
        dropdownItems = screenDropdownItemList.toList(),
        onItemSelected = { currentScreen.value = getCurrentScreen(it.label) },
        onResetButtonClicked = { currentScreen.value = ButtonScreens.NO_COMPONENT_SELECTED },
        selectedItem = DropdownItem(currentScreen.value.label),
        state = InputShellState.UNFOCUSED,
    )
    when (currentScreen.value) {
        ButtonScreens.BUTTON -> ButtonScreen()
        ButtonScreens.BUTTON_BLOCK -> ButtonBlockScreen()
        ButtonScreens.CAROUSEL_BUTTONS -> ButtonCarouselScreen()
        ButtonScreens.CHECK_BOX -> CheckboxScreen()
        ButtonScreens.ICON_BUTTON -> IconButtonScreen()
        ButtonScreens.RADIO -> RadioButtonScreen()
        ButtonScreens.SWITCH -> SwitchScreen()
        ButtonScreens.FAB -> FABScreen()
        ButtonScreens.NO_COMPONENT_SELECTED -> NoComponentSelectedScreen()
    }
}

enum class ButtonScreens(val label: String) {
    BUTTON("Button"),
    BUTTON_BLOCK("Button block"),
    CAROUSEL_BUTTONS("Carousel buttons"),
    CHECK_BOX("Check Box"),
    FAB("FAB"),
    ICON_BUTTON("Icon Button"),
    RADIO("Radio"),
    SWITCH("Switch"),
    NO_COMPONENT_SELECTED("No component selected"),
}

fun getCurrentScreen(label: String): ButtonScreens {
    return ButtonScreens.entries.firstOrNull { it.label == label } ?: ButtonScreens.BUTTON
}
