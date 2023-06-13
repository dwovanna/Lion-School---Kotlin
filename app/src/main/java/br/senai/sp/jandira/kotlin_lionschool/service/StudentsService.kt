package br.senai.sp.jandira.kotlin_lionschool.service

import br.senai.sp.jandira.kotlin_lionschool.model.Student
import br.senai.sp.jandira.kotlin_lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentsService {

    @GET("alunos")
    fun getCourseStudent(@Query("curso") siglaCourse: String): Call<StudentsList>

    @GET("alunos/{matricula}")
    fun getAlunosByMatricula(@Path("matricula") matricula: String): Call<Student>

    @GET("alunos")
    fun getCourseStudentWithStatus(@Query("curso") curso: String, @Query("status") status: String): Call<StudentsList>

}