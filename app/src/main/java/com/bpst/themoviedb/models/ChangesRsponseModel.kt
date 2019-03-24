package com.bpst.themoviedb.models

data class Result(var id: Int, var adult: Boolean)

data class Response(var results: ArrayList<Result>)