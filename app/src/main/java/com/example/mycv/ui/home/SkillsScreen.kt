package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.MyCVTheme
import com.example.mycv.R
import com.example.mycv.data.Item
import com.example.mycv.data.Skill
import com.example.mycv.data.Skills
import com.example.mycv.data.Languages
import com.example.mycv.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SkillsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MyCVTopAppBar(
                title = stringResource(R.string.CVName),
                canNavigateBack = true,
                navigateUp = { navController.navigateUp() }
            )
        },
    ) { innerPadding ->
        MyCVApplicationSkills(
            modifier = Modifier
                .padding(innerPadding)
            //.fillMaxHeight()
        )
    }
}

@Composable
fun MyCVApplicationSkills(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CardTextContainer(
            item = Item(
                title = stringResource(id = R.string.skillsTitle),
                body = stringResource(id = R.string.skillsBody),
            ),
            modifier = Modifier,
            icon = R.drawable.baseline_lightbulb_24,
            bodyMaxLines = 100,
        )
        SkillsDisplayer(stringResource(id = R.string.computers), Skills)
        SkillsDisplayer(stringResource(id = R.string.languages), Languages)
        CardTextContainer(
            Item(
                title = stringResource(id = R.string.otherSkillsTitle),
                body = stringResource(id = R.string.otherSkillsBody),
            ),
            modifier = Modifier,

        )
    }

}

@Composable
fun SkillsDisplayer(title: String, skills: List<Skill>, modifier: Modifier = Modifier){
    val shape = Shapes.small
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small)),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = shape,
        border = BorderStroke(color = MaterialTheme.colorScheme.onTertiaryContainer, width = 1.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                .padding(top = dimensionResource(id = R.dimen.padding_small)),
            text = title,//stringResource(id = R.string.computers),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        LazyColumn(modifier = modifier) {
            itemsIndexed(items = skills){index, skill ->
                SkillDiplay(skill)
                if (index != Skills.size) {
                    Divider(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
        }
    }
}

@Composable
fun SkillDiplay(skill: Skill, modifier: Modifier = Modifier) {
    val competence = skill.competence.competence
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,

    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = skill.name,
            textAlign = TextAlign.Right,
        )
        Row(modifier = Modifier
            .fillMaxHeight()
            .weight(1f)) {
            repeat(competence) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_circle_24),
                    contentDescription = "circle Full",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .size(16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            repeat(5-competence) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_circle_24),
                    contentDescription = "circle empty",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .size(16.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MyCVApplicationSkillsPreview(){
    MyCVTheme {
        MyCVApplicationSkills()
    }
}