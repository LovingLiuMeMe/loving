package cn.lovingliu.lovingmall.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-31
 */
public class GoodsStatusToDesc extends JsonSerializer<Integer>{

    @Override
    public void serialize(Integer sellStatus, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String statusStr = "";
        switch (sellStatus){
            case 0:
                statusStr = "上架";
                break;
            case 1:
                statusStr = "下架";
                break;
            default:
                statusStr = "状态错误";
        }
        gen.writeString(statusStr);
    }
}
