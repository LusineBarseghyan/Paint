package com.example.paint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.input.pointer.pointerInput
import com.example.paint.ui.theme.PaintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaintTheme {
                PaintCanvas()

            }
        }
    }
}


@Composable
fun PaintCanvas() {
    val temp = Path()
    val path = remember {
        mutableStateOf(Path())
    }
    Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(true) {
            detectDragGestures { change, dragAmount ->
                temp.moveTo(
                    change.position.x - dragAmount.x,
                    change.position.y - dragAmount.y
                )
                temp.lineTo(
                    change.position.x,
                    change.position.y
                )
                path.value= Path().apply {
                 addPath(temp)
                }
            }
        }
    ) {
        drawPath(
            path.value,
            color = Color.Black,
            style = Stroke(5f)
        )

    }


}