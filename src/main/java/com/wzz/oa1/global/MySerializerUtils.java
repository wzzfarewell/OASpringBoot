package com.wzz.oa1.global;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: wzzap
 * @Description: 把状态码转换成字符串
 * @Date: 2019/6/6
 **/
public class MySerializerUtils extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = "";
        switch(integer){
            case 0:
                str = "SUCCESS";
                break;
            case 1:
                str = "ERROR";
                break;
            case 2:
                str = "ILLEGAL_ARGUMENT";
                break;
            case 10:
                str = "NEED_LOGIN";
                break;
            default:
                str = "状态信息异常";
        }
        jsonGenerator.writeString(str);
    }
}
