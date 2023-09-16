package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
fun ContactScreen(
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
        MyCVApplicationContact(
            modifier = Modifier
                .padding(innerPadding)
            //.fillMaxHeight()
        )
    }
}

@Composable
fun MyCVApplicationContact(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CardTextContainer(
            item = Item(
            title = stringResource(id = R.string.contactTitle),
            body = stringResource(id = R.string.contactBody),
            ),
            icon = R.drawable.baseline_contact_page_24,
            bodyMaxLines = 100,
        )
        Spacer(modifier = Modifier.weight(1f))
        ImageAtBottom(R.drawable.baseline_contact_page_24)
    }

}


@Preview(showBackground = true)
@Composable
fun MyCVApplicationContactPreview(){
    MyCVTheme() {
        MyCVApplicationContact()
    }
}