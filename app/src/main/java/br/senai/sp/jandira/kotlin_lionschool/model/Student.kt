package br.senai.sp.jandira.kotlin_lionschool.model

data class Student(
    val foto: String,
    val nome: String,
    val matricula: String,
    val status: String,
    val curso: List<Course>
)
