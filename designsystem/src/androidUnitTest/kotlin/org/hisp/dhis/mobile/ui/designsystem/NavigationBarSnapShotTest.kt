package org.hisp.dhis.mobile.ui.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Hub
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnScreenContainer
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBar
import org.hisp.dhis.mobile.ui.designsystem.component.navigationBar.NavigationBarItem
import org.junit.Rule
import org.junit.Test

class NavigationBarSnapShotTest {
    @get:Rule
    val paparazzi = paparazzi()

    @Test
    fun launchNavigationBar() {
        paparazzi.snapshot {
            val homeItems = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.Description, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.Description, contentDescription = null)
                    },
                    label = "Description",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.BarChart, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.BarChart, contentDescription = null)
                    },
                    label = "Charts",
                    showBadge = true,
                ),
            )

            val programItems = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.List, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = null)
                    },
                    label = "List",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.Map, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.Map, contentDescription = null)
                    },
                    label = "Maps",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.BarChart, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.BarChart, contentDescription = null)
                    },
                    label = "Charts",
                    showBadge = true,
                    badgeText = "32",
                ),
            )

            val enrollmentItems = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.Assignment, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.Assignment, contentDescription = null)
                    },
                    label = "Details",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.BarChart, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.BarChart, contentDescription = null)
                    },
                    label = "Charts",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.Hub, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.Hub, contentDescription = null)
                    },
                    label = "Relationships",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.StickyNote2, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.StickyNote2, contentDescription = null)
                    },
                    label = "Notes",
                    showBadge = true,
                ),
            )

            val formItems = listOf(
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.Description, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.Description, contentDescription = null)
                    },
                    label = "Description",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.Outlined.BarChart, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.Filled.BarChart, contentDescription = null)
                    },
                    label = "Charts",
                ),
                NavigationBarItem(
                    defaultIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Outlined.StickyNote2, contentDescription = null)
                    },
                    selectedIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.StickyNote2, contentDescription = null)
                    },
                    label = "Notes",
                    showBadge = true,
                    badgeText = "3",
                ),
            )

            ColumnScreenContainer(
                title = "Navigation Bar",
            ) {
                ColumnComponentContainer("Home") {
                    var selectedHomeItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = homeItems,
                        selectedItemIndex = selectedHomeItemIndex,
                    ) {
                        selectedHomeItemIndex = it
                    }
                }
                ColumnComponentContainer("Program dashboard") {
                    var selectedProgramItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = programItems,
                        selectedItemIndex = selectedProgramItemIndex,
                    ) {
                        selectedProgramItemIndex = it
                    }
                }

                ColumnComponentContainer("Enrollment dashboard") {
                    var selectedEnrollmentItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = enrollmentItems,
                        selectedItemIndex = selectedEnrollmentItemIndex,
                    ) {
                        selectedEnrollmentItemIndex = it
                    }
                }

                ColumnComponentContainer("Form") {
                    var selectedFormItemIndex by remember { mutableStateOf<Int?>(null) }
                    NavigationBar(
                        items = formItems,
                        selectedItemIndex = selectedFormItemIndex,
                    ) {
                        selectedFormItemIndex = it
                    }
                }
            }
        }
    }
}