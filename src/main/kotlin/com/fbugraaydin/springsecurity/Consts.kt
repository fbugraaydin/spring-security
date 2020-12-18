package com.fbugraaydin.springsecurity

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key


const val ADMIN  = "ADMIN"
const val DEFAULT = "DEFAULT"

val SIGNING_KEY = "defaultsignkey"
val TOKEN_PREFIX = "Bearer "
val HEADER_STRING = "Authorization"
val ISSUER = "issuer"
val ACCESS_TOKEN_VALIDITY_SECONDS = (1 * 60 * 60).toLong()

var KEY: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)