package com.example.blogex.common.dto;


import com.example.blogex.common.Code.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultResponse {
    private String code;
    private String message;
    private Object data;

    public static ResultResponse of(ResultCode resultCode, Object data) {
        return new ResultResponse(resultCode,data);
    }

    public ResultResponse(ResultCode resultCode, Object data) {
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
        this.data=data;
    }

}
