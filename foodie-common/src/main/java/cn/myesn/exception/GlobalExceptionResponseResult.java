package cn.myesn.exception;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GlobalExceptionResponseResult {
    private String message;
}
