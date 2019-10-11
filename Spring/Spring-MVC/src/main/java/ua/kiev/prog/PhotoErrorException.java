package ua.kiev.prog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
//Эта онатоция позволяет делать связать факт выкидывания эксепшна с неким статус кодом для ответа клиенту
// это должно привести к возврату некого эрор кода
public class PhotoErrorException extends RuntimeException {}