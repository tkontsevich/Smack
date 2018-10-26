package com.example.tkontsevich.smack.Untilities

const val BASE_URL = "https://chatty-tanya-smack.herokuapp.com/v1/"
const val SOCKET__URL = "https://chatty-tanya-smack.herokuapp.com/"
const val URL_REGISTER = "${BASE_URL}account/register"
const val URL_LOGIN = "${BASE_URL}account/login"
const val URL_CREATE_USER = "${BASE_URL}user/add"
const val URL_GET_USER = "${BASE_URL}user/byEmail/"
const val URL_GET_CHANNEL = "${BASE_URL}channel"
const val URL_GET_MESSAGES = "${BASE_URL}message/byChannel/"

// Broadcast
const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"