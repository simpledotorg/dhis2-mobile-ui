package org.hisp.dhis.mobile.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.SyncProblem
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor
import org.junit.Rule
import org.junit.Test

class ListCardTest {

    @get:Rule
    val rule = createComposeRule()

    val additionalItemList = listOf(
        AdditionalInfoItem(key = "Phone:", value = "+234 123 111 6785"),
        AdditionalInfoItem(key = "Date of birth:", value = "12/12/1945"),
        AdditionalInfoItem(key = "Address:", value = "134 Main Road, Behind the temple, Citytown, Basil District, Granite State"),
        AdditionalInfoItem(key = "Enrolled in:", value = "12/12/1945"),
        AdditionalInfoItem(
            key = "Programs:",
            value = "Tuberculosis, Nutrition \n" +
                "Assistance Program, Malaria Diagnosis",
        ),
        AdditionalInfoItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Icon Button",
                    tint = TextColor.OnDisabledSurface,
                )
            },
            value = "Enrollment cancelled",
            color = TextColor.OnDisabledSurface,
            isConstantItem = true,
        ),
        AdditionalInfoItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.SyncProblem,
                    contentDescription = "Icon Button",
                    tint = TextColor.OnWarning,
                )
            },
            value = "Sync warning",
            color = TextColor.OnWarning,
            isConstantItem = true,
        ),
        AdditionalInfoItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.EventBusy,
                    contentDescription = "Icon Button",
                    tint = SurfaceColor.Error,
                )
            },
            value = "Baby post natal overdue 6 days",
            color = SurfaceColor.Error,
            isConstantItem = true,
        ),
    )

    @Test
    fun shouldDisplayListCardCorrectly() {
        rule.setContent {
            ListCard(
                modifier = Modifier.testTag("LIST_CARD"),
                listAvatar = {
                    Avatar(
                        textAvatar = "X",
                        style = AvatarStyle.TEXT,
                    )
                },
                title = "Anita Mathews, F, 72",
                lastUpdated = "5 hours",
                additionalInfoList = additionalItemList,
                actionButton = {
                    Button(
                        style = ButtonStyle.TONAL,
                        text = "Retry sync",
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Sync,
                                contentDescription = "Icon Button",
                                tint = TextColor.OnPrimaryContainer,
                            )
                        },
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().testTag("LIST_CARD_ACTION_BUTTON"),
                    )
                },
                onCardClick = {},
            )
        }
        rule.onNodeWithTag("LIST_CARD").assertExists()
        rule.onNodeWithTag("LIST_CARD_ACTION_BUTTON").assertExists()
    }
}
