package br.senai.sp.jandira.kotlin_lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.kotlin_lionschool.model.Courses
import br.senai.sp.jandira.kotlin_lionschool.model.CoursesList
import br.senai.sp.jandira.kotlin_lionschool.model.Students
import br.senai.sp.jandira.kotlin_lionschool.service.RetrofitFactory
import br.senai.sp.jandira.kotlin_lionschool.ui.theme.KotlinLionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLionSchoolTheme{
               Home()

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Home() {
    val context = LocalContext.current
    var listCourses by remember {
        mutableStateOf(listOf<Courses>())
    }

    val call = RetrofitFactory().getCourseService().getCourse()

    call.enqueue(object : Callback<CoursesList> {
        override fun onResponse(call: Call<CoursesList>, response: Response<CoursesList>) {
            listCourses = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CoursesList>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message}")
        }
    })

    Log.i("TAG", "$listCourses")
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
        Box(modifier = Modifier
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
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(100.dp)
                        .padding(5.dp)
                )


                Text(text = "Lion School",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.width(100.dp)

                )
                Spacer(modifier = Modifier.width(80.dp))

                Icon(painter = painterResource(id = R.drawable.sair),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                )

                Text(text = "Sair",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(150.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                    Text(text = "Escolha um curso para Gerenciar",
                        color = Color.White,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif

                    )

                     Spacer(modifier = Modifier.width(10.dp))

                    Image(painter = painterResource(id = R.drawable.desktop),
                        contentDescription = null,
                        modifier = Modifier
                            .width(230.dp)
                            .height(193.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(listCourses) {

                            Spacer(modifier = Modifier.size(20.dp))
                            Card(
                                modifier = Modifier
                                    .width(280.dp)
                                    .height(110.dp)
                                    .clickable {
                                        var openStudents = Intent(context, Cursos::class.java)
                                        openStudents.putExtra("sigla", it.sigla)

                                        context.startActivity(openStudents)
                                    }
                                    .border(
                                        1.5.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                backgroundColor = Color(57, 90, 213, 255),
                                shape = RoundedCornerShape(20.dp),

                            ) {
                                Row(modifier = Modifier.padding(8.dp)) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ds),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(70.dp)
                                    )

                                    Spacer(modifier = Modifier.size(10.dp))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                        text = it.sigla,
                                            fontSize = 35.sp,
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.Serif
                                        )

                                        Spacer(modifier = Modifier.size(10.dp))

                                        Text(text = it.nome,
                                            fontSize = 13.sp,
                                            color = Color.White,
                                            fontFamily = FontFamily.Serif

                                        )
                                    }
                                }

                            }
                        }
                    }
            }
        }
    }
}