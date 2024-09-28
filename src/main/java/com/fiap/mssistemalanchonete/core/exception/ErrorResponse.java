package com.fiap.mssistemalanchonete.core.exception;

public record ErrorResponse (
   String timestamp,
   int status,
   String error,
   String path
){
}
