package br.senai.sp.jandira.kotlin_lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.kotlin_lionschool.model.Student
import br.senai.sp.jandira.kotlin_lionschool.service.RetrofitFactory
import br.senai.sp.jandira.kotlin_lionschool.ui.theme.KotlinLionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Aluno : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLionSchoolTheme{
                val matriculaAluno = intent.getStringExtra("matricula")
                Dados(matriculaAluno.toString())

            }
        }
    }
}

@Composable
fun Dados(matricula: String) {
    val context = LocalContext.current

    var student by remember {
        mutableStateOf(Student("", "", "", emptyList()))
    }

    val call = RetrofitFactory().getStudentsService().getAlunosByMatricula(matricula)

    call.enqueue(object : Callback<Student> {
        override fun onResponse(call: Call<Student>, response: Response<Student>) {
            if (response.isSuccessful) {
                val studentResponse = response.body()
                if (studentResponse != null) {
                    student = studentResponse
                }
            } else {
                Log.e("teste", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Student>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
        }
    })

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
        Column() {
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


                }

            }

            Column(
                modifier = Modifier.fillMaxSize(),
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
                        items(student.curso) {
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
                                    AsyncImage(
                                        model = student.foto,
                                        contentDescription = "",
                                        modifier = Modifier.size(130.dp)
                                    )


                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        Text(
                                            text = "JOSÉ MATHEUS DA SILVA MIRANDA",
                                            fontSize = 20.sp,
                                            color = Color(51, 71, 176, 255),
                                            fontFamily = FontFamily.SansSerif,
                                            modifier = Modifier
                                                .padding(8.dp)


                                        )
                                        LazyColumn(
                                            modifier = Modifier.fillMaxSize(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            items(student.curso[0].disciplinas) {
                                                var barra = 2.4 * it.media.toDouble()
                                                var corBarra =
                                                    colorResource(id = R.color.blue_default)

                                                if (it.media.toDouble() > 60) {
                                                    corBarra =
                                                        colorResource(id = R.color.blue_default)
                                                } else if (it.media.toDouble() < 60 && it.media.toDouble() > 50) {
                                                    corBarra =
                                                        colorResource(id = R.color.yellow_default)
                                                } else {
                                                    corBarra = Color.Red
                                                }
                                                Column(
                                                    modifier = Modifier
                                                        .width(240.dp)
                                                        .height(40.dp)
                                                ) {
                                                    Text(
                                                        text = it.nome,
                                                        fontWeight = FontWeight(700),
                                                        fontSize = 12.sp,
                                                        color = colorResource(id = R.color.black)
                                                    )
                                                    Spacer(modifier = Modifier.height(2.dp))
                                                    Box(
                                                        modifier = Modifier
                                                            .height(17.5.dp)
                                                            .width(240.dp)
                                                            .clip(RoundedCornerShape(10.dp))
                                                            .background(
                                                                colorResource(id = R.color.second_blue)
                                                            )
                                                    ) {
                                                        Box(
                                                            modifier = Modifier
                                                                .fillMaxHeight()
                                                                .clip(RoundedCornerShape(10.dp))
                                                                .background(
                                                                    corBarra
                                                                )
                                                                .width(barra.dp)
                                                                .padding(0.dp, 0.dp, 5.dp, 0.dp),
                                                            contentAlignment = Alignment.CenterEnd
                                                        ) {
                                                            Text(
                                                                text = it.media + "%",
                                                                fontWeight = FontWeight(700),
                                                                fontSize = 12.sp,
                                                                color = colorResource(id = R.color.white)
                                                            )
                                                        }
                                                    }
                                                }

                                                Spacer(modifier = Modifier.height(15.dp))


                                            }
                                        }

                                    }
                                }
                            }

                        }

                    }
                }


            }
        }
    }
}