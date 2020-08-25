package com.example.kotlin_mvvm.utill

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message : String) : IOException(message)