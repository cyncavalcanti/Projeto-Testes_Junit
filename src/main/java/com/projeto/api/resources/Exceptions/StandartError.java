package com.projeto.api.resources.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandartError {


    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
