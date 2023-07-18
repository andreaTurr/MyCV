package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.compose.MyCVTheme
import com.example.mycv.R
import com.example.mycv.data.Item

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IntroductionScreen(
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
        MyCVApplicationIntroduction(
            modifier = Modifier
                .padding(innerPadding)
            //.fillMaxHeight()
        )
    }
}

@Composable
fun MyCVApplicationIntroduction(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CardTextContainer(
            Item(
                title = stringResource(id = R.string.introductionTitle),
                body = stringResource(id = R.string.introductionBody),
            ),
            Modifier.fillMaxWidth(),
            bodyMaxLines = 100,
        )
        CardTextContainer(
            Item(
                title = stringResource(id = R.string.passionsTitle),
                body = stringResource(id = R.string.passionsBody),
            ),
            Modifier.fillMaxWidth(),
            bodyMaxLines = 100,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyCVApplicationIntroductionPreview(){
    MyCVTheme() {
        MyCVApplicationIntroduction()
    }
}