package br.senai.sp.jandira.kotlin_lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.kotlin_lionschool.ui.theme.KotlinLionSchoolTheme

class Aluno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLionSchoolTheme{
                Dados()

            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Dados() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(25, 43, 202, 255),
                        Color(40, 73, 196, 255),
                        Color(55, 75, 211, 255),
                        Color(78, 111, 236, 255),
                        Color(117, 141, 233, 255),
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .height(96.dp)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(27, 48, 241, 255),
                            Color(77, 110, 235, 255),
                            Color(137, 156, 228, 255),
                        )
                    )
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(100.dp)
                        .padding(5.dp)
                )

                Text(
                    text = "Lion School",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.width(100.dp)

                )
                Spacer(modifier = Modifier.width(80.dp))

                Icon(
                    painter = painterResource(id = R.drawable.sair),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                )

                Text(
                    text = "Sair",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.size(28.dp))

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(4) {
                                Spacer(modifier = Modifier.size(20.dp))
                                Card(
                                    modifier = Modifier
                                        .width(280.dp)
                                        .height(95.dp)
                                        .border(
                                            2.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(30.dp)
                                        ),
                                    backgroundColor = Color(252, 249, 240, 255),
                                    shape = RoundedCornerShape(30.dp),
                                ) {
                                    Row(modifier = Modifier.padding(8.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.perfil),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(70.dp)
                                        )

                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                            Text(
                                                text = "JOSÉ MATHEUS DA SILVA MIRANDA",
                                                fontSize = 20.sp,
                                                color = Color.White,
                                                fontFamily = FontFamily.SansSerif


                                            )

                                        }
                                    }

                                }
                            }
                        }

                    }
                    Column(

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.footer),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .width(250.dp)
                                .height(350.dp)
                        )

                    }
                }
            }

        }
    }
}