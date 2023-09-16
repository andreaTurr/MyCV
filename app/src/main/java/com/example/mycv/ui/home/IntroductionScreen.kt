package com.example.mycv.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
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
        /*Spacer(modifier = Modifier.height(20.dp))
        CustomCardCloud(
            Item(
                title = stringResource(id = R.string.introductionTitle),
                body = stringResource(id = R.string.introductionBody),
            ),
        )*/
        /*Icon(
            painter = painterResource(id = R.drawable.baseline_school_24),
            contentDescription = stringResource(id = R.string.icon),
            //contentScale = ContentScale.Crop,
            tint = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxSize()
                //.size(64.dp)
        )*/
    }
}

@Composable
fun CustomCardCloud(
    item: Item,
    modifier: Modifier = Modifier
){
    var color = MaterialTheme.colorScheme.tertiaryContainer
    color = color.copy(alpha = 1f)
    var borderColor = MaterialTheme.colorScheme.onTertiaryContainer
    borderColor = borderColor.copy(alpha = 0.5f)
    Card(
        shape = CloudShape(),
        modifier= modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        border = BorderStroke(color = borderColor, width = 1.dp),
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_large)),
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = item.body,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}


class CloudShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawCloudPath(size = size)
        )
    }
}

val CloudShapeTestBorder = GenericShape { size, _ ->
    val path =  drawCloudPath(size = size)
    val matrix = Matrix()
    val pathSize = path.getBounds().size
    matrix.scale(size.width / pathSize.width, size.height / pathSize.height)
    path.transform(matrix)
    addPath(path)

}


fun drawCloudPath(size: Size): Path {
    return Path().apply {
        val width = size.width
        val height = size.height
        //18:x=width:heigh
        val hSideCurves = (size.width/50).toInt()
        val vSideCurves = ((hSideCurves * height) / width).toInt()

        val offsetRatio = 0.5f

        //horizontal offset is a quarter a unit per site
        //val hOffsetCorner = size.width/(4*hSideCurves+2f)

        val hDiameter = width / (hSideCurves + offsetRatio*2)

        val hOffsetCorner = hDiameter*offsetRatio
        val vOffsetCorner = hSideCurves / 2f



        val vDiameterV = (height-hDiameter) / (vSideCurves)
        val vDiameterH = hOffsetCorner*2

        //internal rectangle drwn bt the semi circles
        val rightIntSide = width-hOffsetCorner
        val bottomIntSide = height-hDiameter/2
        //--  END --//


        val intRect = Rect(
                Offset(hOffsetCorner, vOffsetCorner),
                Size(width-2*hOffsetCorner, height-2*vOffsetCorner)
            )
        //val rightIntSide = intRect.right
        //val hCurveSize = (intRect.left-intRect.right)/hSideCurves
        //val vCurveSize = (intRect.bottom-intRect.top)/vSideCurves

        reset()
        repeat(hSideCurves.toInt()){
            arcTo(
                rect =
                Rect(
                    Offset(hOffsetCorner + it * hDiameter, 0f),
                    Size(hDiameter, hDiameter)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
        }


        repeat(vSideCurves.toInt()){
            arcTo(
                rect = Rect(
                    Offset(rightIntSide-vDiameterH/2, hDiameter/2+it*vDiameterV),
                    Size(vDiameterH, vDiameterV)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
        }

        repeat(hSideCurves.toInt()){
            arcTo(
                rect =
                Rect(
                    Offset(rightIntSide-hDiameter*(it+1), bottomIntSide-hDiameter/2),
                    Size(hDiameter, hDiameter)
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
        }
        repeat(vSideCurves.toInt()){
            arcTo(
                rect =
                Rect(
                    Offset(0f, bottomIntSide-vDiameterV*(it+1)),
                    Size(vDiameterH, vDiameterV)
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
        }/*

       // go from (0,0) to (width, 0)
        lineTo(size.width, 0f)

        // go from (width, 0) to (width, height)
        lineTo(size.width, size.height)

        // Draw an arch from (width, height) to (0, height)
        // starting from 0 degree to 180 degree
        arcTo(
            rect =
            Rect(
                Offset(0f, 0f),
                Size(size.width, size.height)
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        // go from (0, height) to (0, 0)
        lineTo(0f, 0f)*/
        close()
    }
}

@Preview(showBackground = true)
@Composable
fun MyCVApplicationIntroductionPreview(){
    MyCVTheme() {
        MyCVApplicationIntroduction()
    }
}

@Preview(showBackground = true)
@Composable
fun CustomCloudPreview(){
    MyCVTheme() {
        CustomCardCloud(
            Item(
                title = stringResource(id = R.string.introductionTitle),
                body = stringResource(id = R.string.introductionBody),
            )
        )
    }
}
