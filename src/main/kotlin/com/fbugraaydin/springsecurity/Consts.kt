package com.fbugraaydin.springsecurity

const val ADMIN  = "ADMIN"
const val DEFAULT = "DEFAULT"

val SIGNING_KEY = "defaultsignkey"
val TOKEN_PREFIX = "Bearer "
val HEADER_STRING = "Authorization"
val ACCESS_TOKEN_VALIDITY_SECONDS = (5 * 60 * 60).toLong()