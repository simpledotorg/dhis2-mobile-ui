package org.hisp.dhis.common.screens.bottomSheets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.hisp.dhis.mobile.ui.designsystem.component.BottomSheetHeader
import org.hisp.dhis.mobile.ui.designsystem.component.ColumnComponentContainer
import org.hisp.dhis.mobile.ui.designsystem.component.SubTitle
import org.hisp.dhis.mobile.ui.designsystem.theme.Spacing
import org.hisp.dhis.mobile.ui.designsystem.theme.SurfaceColor
import org.hisp.dhis.mobile.ui.designsystem.theme.TextColor

@Composable
fun BottomSheetHeaderScreen() {
    ColumnComponentContainer(title = "Bottom Sheet Header") {
        SubTitle("With Icon", TextColor.OnSurface)
        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                subTitle = "Subtitle",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                },
            )
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Without Icon", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                subTitle = "Subtitle",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
            )
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Without Icon, without subtitle", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
            )
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("Without Icon, subtitle or description", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
            )
        }
        Spacer(Modifier.size(Spacing.Spacing18))

        SubTitle("With Icon, without subtitle or description", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                },
            )
        }
        SubTitle("Bottom sheet shell with header, content and buttons", TextColor.OnSurface)

        Box(modifier = Modifier.border(Spacing.Spacing1, color = TextColor.OnDisabledSurface)) {
            BottomSheetHeader(
                title = "Title",
                subTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce convallis, urna vitae lacinia feugiat",
                headerTextAlignment = TextAlign.Start,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = "Button",
                        tint = SurfaceColor.Primary,
                    )
                },
            )
        }
        SubTitle("Bottom sheet shell with header text aligned to start", TextColor.OnSurface)
    }
}
