package br.senai.sp.jandira.kotlin_lionschool.service

import br.senai.sp.jandira.kotlin_lionschool.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CoursesService {

    @GET("cursos")
    fun getCourse(): Call<CoursesList>
}