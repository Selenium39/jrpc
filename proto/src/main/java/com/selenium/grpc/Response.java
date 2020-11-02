package com.selenium.grpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer code = 0; // 0 success
    private String msg = "success";
    private Object data;
}
