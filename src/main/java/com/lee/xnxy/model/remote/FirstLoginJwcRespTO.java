package com.lee.xnxy.model.remote;

import lombok.Data;

@Data
public class FirstLoginJwcRespTO {
    private FirstLoginJwcRespDataTO data;
    private Integer code;
    private String msg;
}
