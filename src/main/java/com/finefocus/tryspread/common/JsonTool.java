package com.finefocus.tryspread.common;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.common
 * @date 2016/7/7
 * @Description: ${todo}
 */
public class JsonTool {
    public static final ObjectMapper mapper = new ObjectMapper();

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
