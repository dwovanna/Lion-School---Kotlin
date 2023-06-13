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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.senai.sp.jandira.kotlin_lionschool.model.Students
import br.senai.sp.jandira.kotlin_lionschool.model.StudentsList
import br.senai.sp.jandira.kotlin_lionschool.service.RetrofitFactory
import br.senai.sp.jandira.kotlin_lionschool.ui.theme.KotlinLionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cursos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinLionSchoolTheme {
                val siglaCurso = intent.getStringExtra("sigla")
                DefaultPreview(siglaCurso.toString())

            }
        }
    }
}

@Composable
fun DefaultPreview(curso: String) {
    val context = LocalContext.current

    // Estado para controlar a opção selecionada
    var selectedOption by remember { mutableStateOf(1) }

    var listStudents by remember {
        mutableStateOf(listOf<Students>())
    }

    var nameCourse by remember {
        mutableStateOf("")
    }

    val call = RetrofitFactory().getStudentsService().getCourseStudent(curso)

    call.enqueue(object : Callback<StudentsList> {
        override fun onResponse(call: Call<StudentsList>, response: Response<StudentsList>) {
            listStudents = response.body()!!.alunos
            nameCourse = response.body()!!.nomeCurso
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
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

        Spacer(modifier = Modifier.size(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(115.dp))

                Card(
                    modifier = Modifier
                        .width(350.dp)
                        .height(30.dp)
                        .border(
                            2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(30.dp)
                        )

                ) {


                    Spacer(modifier = Modifier.size(115.dp))

                    Row(modifier = Modifier.padding(5.dp)) {
                        Text(
                            text = "Pesquisar",
                            color = Color.Gray,
                        )
                    }
                }
                Column(

                ) {
                    Spacer(modifier = Modifier.size(15.dp))
                    Row() {
                        ToggleButton(selectedOption, onOptionSelected = { option ->
                            selectedOption = option
                            // Lógica para tratar a opção selecionada
                            when (option) {
                                1 -> {
                                    // Opção 1 selecionada
                                    // Faça algo aqui
                                }
                                2 -> {
                                    // Opção 2 selecionada
                                    // Faça algo aqui
                                }
                                3 -> {
                                    // Opção 3 selecionada
                                    // Faça algo aqui
                                }
                            }
                        })
                    }

                }

                Spacer(modifier = Modifier.size(28.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(listStudents) {
                        var backgroundCard = Color(0, 0, 0)
                        if (it.status == "Finalizado") {
                            backgroundCard = Color(51, 71, 176, 255)
                        } else {
                            backgroundCard = Color(229, 182, 87, 255)
                        }
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
                            backgroundColor = backgroundCard,
                            shape = RoundedCornerShape(30.dp),
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                AsyncImage(
                                    model = it.foto,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(70.dp)
                                )

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                    Text(
                                        text = it.nome,
                                        fontSize = 20.sp,
                                        color = Color.White,
                                        fontFamily = FontFamily.SansSerif


                                    )

                                }
                            }

                        }
                    }
                }
                Column(

                ) {
                    Icon(painter = painterResource(id = R.drawable.footer),
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
@Composable
fun ToggleButton(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val options = listOf("Todos", "Cursando", "Finalizado")
        options.forEachIndexed { index, option ->
            val isSelected = index + 1 == selectedOption
            Button(
                onClick = { onOptionSelected(index + 1) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 6.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected) colorResource(id = R.color.blue_default) else colorResource(
                        id = R.color.second_blue
                    ),
                    contentColor = if (isSelected) colorResource(id = R.color.yellow_default) else colorResource(
                        id = R.color.blue_default
                    )
                )
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.body1,
                    fontSize = 16.sp

                )
            }
        }
    }
}