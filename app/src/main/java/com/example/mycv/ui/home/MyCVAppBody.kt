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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
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

enum class PositionCard {
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
            Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_small)),
            onclick = { navController.navigate(ROUTES.INTRODUCTION.route) },
            bodyMaxLines = 10,
        )
        Column(Modifier.weight(1f)) {

            CardTextContainer(
                item = Item(
                    title = stringResource(id = R.string.educationTitle),
                    body = stringResource(id = R.string.educationBody),
                ),
                onclick = { navController.navigate(ROUTES.EDUCATION.route) },
                icon = R.drawable.baseline_school_24,
                positionCard = PositionCard.RIGHT,
                color = colorResource(id = R.color.tertiaryContainerEducation),
                cloudShape = true,
            )
            CardTextContainer(
                item = Item(
                title = stringResource(id = R.string.careerTitle),
                body = stringResource(id = R.string.careerBody),
                ),
                onclick = { navController.navigate(ROUTES.CAREER.route) },
                icon = R.drawable.baseline_business_center_24,
                positionCard = PositionCard.LEFT,
                color = colorResource(id = R.color.tertiaryContainerGreen),
                cloudShape = true,
            )
            CardTextContainer(
                item = Item(
                    title = stringResource(id = R.string.skillsTitle),
                    body = stringResource(id = R.string.skillsBody),
                ),
                onclick = { navController.navigate(ROUTES.SKILLS.route) },
                icon = R.drawable.baseline_lightbulb_24,
                positionCard = PositionCard.RIGHT,
                color = colorResource(id = R.color.tertiaryContainerSkills),
                cloudShape = true,
            )
            CardTextContainer(
                item = Item(
                title = stringResource(id = R.string.contactTitle),
                body = stringResource(id = R.string.contactBody),
                ),
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
                onclick = { navController.navigate(ROUTES.CONTACTS.route) },
                //position = Position.RIGHT,
                icon = R.drawable.baseline_contact_page_24,
                bodyMaxLines = 10,
                showIconWhenCentered = true,
            )
        }
    }
}


@Composable
fun CardTextContainer(
    item: Item,
    modifier: Modifier = Modifier,//.padding(dimensionResource(id = R.dimen.padding_small)),
    onclick: (() -> Unit)? = null,
    @DrawableRes icon: Int = 0,
    positionCard: PositionCard = PositionCard.CENTER,
    bodyMaxLines: Int = 1,
    showIconWhenCentered: Boolean = false,
    color: Color = MaterialTheme.colorScheme.tertiaryContainer,
    cloudShape: Boolean = false,

) {
    val alignmentText = when (positionCard){
        PositionCard.RIGHT -> Alignment.End
        PositionCard.LEFT -> Alignment.Start
        PositionCard.CENTER -> Alignment.Start
    }
    val arrangementCard = when (positionCard) {
        PositionCard.RIGHT -> Arrangement.End
        PositionCard.LEFT -> Arrangement.Start
        PositionCard.CENTER -> Arrangement.Start

    }
    var cardShape : Shape = when (positionCard){
        PositionCard.RIGHT -> CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
        PositionCard.LEFT -> CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp)
        PositionCard.CENTER -> CutCornerShape(8.dp)
    }
    //check clickable
    val cardModifier = if (onclick == null) Modifier else Modifier.clickable { onclick() }
    var borderColor = MaterialTheme.colorScheme.onTertiaryContainer
    var cardBorder = BorderStroke(color = borderColor, width = 1.dp)
    var cardElevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

    if (cloudShape){
        cardBorder = BorderStroke(color = borderColor.copy(0.5f), width = 1.dp)
        cardElevation = CardDefaults.cardElevation()
        cardShape = CloudShape()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_small),
                vertical = dimensionResource(id = R.dimen.padding_very_small)
            ),
        horizontalArrangement = arrangementCard
    ) {
        if (positionCard == PositionCard.RIGHT)
            Spacer(Modifier.weight(1f))
        Card(
            shape = cardShape,
            modifier = cardModifier
                .weight(3f),
            elevation = cardElevation,
            border = cardBorder,
            colors =  CardDefaults.cardColors(containerColor = color)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_very_small)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(positionCard==PositionCard.RIGHT)
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
                if(positionCard==PositionCard.LEFT || showIconWhenCentered)
                    CardTextContainerIcon(icon, Modifier.weight(1f))
            }

        }
        if (positionCard == PositionCard.LEFT )
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