package io.github.biezhi.mybatis;

import lombok.Data;

@Data
public class TransactionToken {
    private Long   id          = -1L;
    private String transaction = "";
    private String token       = "";

}