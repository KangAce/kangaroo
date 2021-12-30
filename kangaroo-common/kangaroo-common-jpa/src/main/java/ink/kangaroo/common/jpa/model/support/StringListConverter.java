package ink.kangaroo.common.jpa.model.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return (String) JSONArray.toJSON(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData==null){
            return null;
        }
        JSONArray parse = (JSONArray) JSON.parse(dbData);
        return parse.stream().map(Object::toString).collect(Collectors.toList());
    }
}
