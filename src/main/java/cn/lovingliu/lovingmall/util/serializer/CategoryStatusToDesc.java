package cn.lovingliu.lovingmall.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 在
 * @Date：Created in 2019-10-31
 */
public class CategoryStatusToDesc extends JsonSerializer<Byte> {
    @Override
    public void serialize(Byte isDeleted, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String statusStr = "状态错误";
        switch (isDeleted){
            case 0:
                statusStr = "正常";
                break;
            case 1:
                statusStr = "已删除";
                break;
        }
        gen.writeString(statusStr);
    }
}
