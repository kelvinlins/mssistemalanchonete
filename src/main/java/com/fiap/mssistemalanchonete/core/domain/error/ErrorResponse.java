package com.fiap.mssistemalanchonete.core.domain.error;

public record ErrorResponse (
   String timestamp,
   int status,
   String error,
   String path
){
}
