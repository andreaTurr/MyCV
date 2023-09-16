package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.MyCVTheme
import com.example.mycv.R
import com.example.mycv.data.Career
import com.example.mycv.data.CareerAndEducationItem
import com.example.mycv.data.Item

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CareerScreen(
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
        MyCVApplicationCareer(
            modifier = Modifier
                .padding(innerPadding)
            //.fillMaxHeight()
        )
    }
}

@Composable
fun MyCVApplicationCareer(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            //verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(dimensionResource(id = R.dimen.padding_small))
        ){
            items(items = Career){item->
                CareerContainer(
                    careerItem = item,
                    color = colorResource(id = R.color.tertiaryContainerGreen)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ImageAtBottom(R.drawable.baseline_business_center_24)
    }
}

@Composable
fun CareerContainer(
    careerItem: CareerAndEducationItem,
    modifier: Modifier = Modifier
        .padding(dimensionResource(id = R.dimen.padding_small)),
    onclick:  () -> Unit = {},
    @DrawableRes icon: Int = 0,
    color: Color = MaterialTheme.colorScheme.tertiaryContainer
) {
    var shape : Shape = CutCornerShape(dimensionResource(id = R.dimen.padding_small))// CutCornerShape(8.dp)
    shape = CloudShape()
    val borderColor = MaterialTheme.colorScheme.onTertiaryContainer
    var border = BorderStroke(color = borderColor.copy(alpha = 0.5f), width = 1.dp)
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .clickable { onclick() }
                //.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                .weight(3f),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
            shape = shape,
            border = border,
            colors = CardDefaults.cardColors(
                containerColor = color)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_large))
                        .weight(4f),
                ) {
                    Text(
                        text = stringResource(id = careerItem.date) + " | " + stringResource(id = careerItem.lenght),
                        style = MaterialTheme.typography.labelSmall,
                        //color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = careerItem.title),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Text(
                        text = stringResource(id = careerItem.body),
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyCVApplicationCareerPreview(){
    MyCVTheme() {
        MyCVApplicationCareer()
    }
}


