package cn.lovingliu.lovingmall.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author：LovingLiu
 * @Description: 实现返回给前端 格式化时间
 * @Date：Created in 2019-10-31
 */
public class DateToDateFormat extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }
}
