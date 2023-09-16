package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.MyCVTheme
import com.example.mycv.R
import com.example.mycv.data.Education

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EducationScreen(
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
        MyCVApplicationEducation(
            modifier = Modifier
                .padding(innerPadding)
            //.fillMaxHeight()
        )
    }
}

@Composable
fun MyCVApplicationEducation(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        /*CardTextContainer(
            item = Item(
                title = stringResource(id = R.string.educationTitle),
                body = stringResource(id = R.string.educationBody),
            ),
            icon = R.drawable.baseline_school_24,
            bodyMaxLines = 100,
        )*/
        LazyColumn(){
            items(items = Education){item->
                CareerContainer(
                    careerItem = item,
                    color = colorResource(id = R.color.tertiaryContainerEducation)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ImageAtBottom(R.drawable.baseline_school_24)
    }

}

@Composable
fun ImageAtBottom(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = image),
        contentDescription = stringResource(id = R.string.icon),
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(
            MaterialTheme.colorScheme.onTertiaryContainer,
            //blendMode = BlendMode.Darken
        ),
        //tint = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxSize()
    )
}


@Preview(showBackground = true)
@Composable
fun MyCVApplicationEducationPreview(){
    MyCVTheme() {
        MyCVApplicationEducation()
    }
}
