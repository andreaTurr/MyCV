package com.example.mycv.ui.home

import ROUTES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyCVTheme
import com.example.mycv.R
import com.example.mycv.data.Item

enum class Position {
    LEFT, RIGHT, CENTER
}

@Composable
fun MyCVApplicationMainBody(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier,
){
    Column(modifier = modifier) {
        CardTextContainer(
            Item(
                title = stringResource(id = R.string.introductionTitle),
                body = stringResource(id = R.string.introductionBody),
            ),
            Modifier.fillMaxWidth(),
            onclick = { navController.navigate(ROUTES.INTRODUCTION.route) },
            bodyMaxLines = 3,
        )
        Column(Modifier.weight(1f)) {
            CardTextContainer(item = Item(
                title = stringResource(id = R.string.careerTitle),
                body = stringResource(id = R.string.careerBody),
                ),
                icon = R.drawable.baseline_business_center_24,
                onclick = { navController.navigate(ROUTES.CAREER.route) },
                position = Position.LEFT
            )
            CardTextContainer(item = Item(
                title = stringResource(id = R.string.educationTitle),
                body = stringResource(id = R.string.educationBody),
                ),
                icon = R.drawable.baseline_school_24,
                onclick = { navController.navigate(ROUTES.EDUCATION.route) },
                position = Position.RIGHT
            )
            CardTextContainer(item = Item(
                    title = stringResource(id = R.string.skillsTitle),
                    body = stringResource(id = R.string.skillsBody),
                ),
                icon = R.drawable.baseline_lightbulb_24,
                onclick = { navController.navigate(ROUTES.SKILLS.route) },
                position = Position.LEFT
            )
            CardTextContainer(item = Item(
                title = stringResource(id = R.string.contactTitle),
                body = stringResource(id = R.string.contactBody),
                ),
                icon = R.drawable.baseline_contact_page_24,
                onclick = { navController.navigate(ROUTES.CONTACTS.route) },
                position = Position.RIGHT
            )
        }
    }
}


@Composable
fun CardTextContainer(
    item: Item,
    modifier: Modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
    onclick:  () -> Unit = {},
    @DrawableRes icon: Int = 0,
    position: Position = Position.CENTER,
    bodyMaxLines: Int = 1
) {
    val alignmentText = when (position){
        Position.RIGHT -> Alignment.End
        Position.LEFT -> Alignment.Start
        Position.CENTER -> Alignment.Start
    }
    val arrangementCard = when (position) {
        Position.RIGHT -> Arrangement.End
        Position.LEFT -> Arrangement.Start
        Position.CENTER -> Arrangement.Start
    }
    val shape = when (position){
        Position.RIGHT -> CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
        Position.LEFT -> CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
        Position.CENTER -> CutCornerShape(8.dp)
    }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = arrangementCard
    ) {
        if (position == Position.RIGHT)
            Spacer(Modifier.weight(1f))
        Card(
            modifier = Modifier
                .clickable { onclick() }
                .padding(dimensionResource(id = R.dimen.padding_small))
                .weight(3f),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = shape,
            border = BorderStroke(color = MaterialTheme.colorScheme.onTertiaryContainer, width = 1.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(position==Position.RIGHT)
                    CardTextContainerIcon(icon, Modifier.weight(1f))
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
                    horizontalAlignment = alignmentText,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_large))
                        .weight(4f),
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = item.body,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = bodyMaxLines,
                        overflow = TextOverflow.Ellipsis,
                        //color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                if(position==Position.LEFT)
                    CardTextContainerIcon(icon, Modifier.weight(1f))
            }

        }
        if (position == Position.LEFT)
            Spacer(Modifier.weight(1f))
    }

}
@Composable
fun CardTextContainerIcon(@DrawableRes icon: Int, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.icon),
            //contentScale = ContentScale.Crop,
            tint = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .size(64.dp)
        )
    }

}

/*@Composable
private fun TextContainerPlusSpacer(
    item: Item, modifier: Modifier = Modifier,
    shape: Shape = Shapes.medium,
    position: Position = Position.LEFT
) {
    Row(modifier = modifier.fillMaxWidth()) {
        TextContainer(
            item,
            position = position,
        )
    }
}*/




@Preview(showBackground = true)
@Composable
fun MyCVApplicationBodyPreview() {
    MyCVTheme {
        MyCVApplicationMainBody(modifier = Modifier.fillMaxSize())
    }
}