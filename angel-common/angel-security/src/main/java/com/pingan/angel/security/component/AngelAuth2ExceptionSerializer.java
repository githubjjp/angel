package com.pingan.angel.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.pingan.angel.common.core.constant.CommonConstants;
import com.pingan.angel.security.exception.AngelAuth2Exception;
import lombok.SneakyThrows;

/**
 * <p>
 * OAuth2 异常格式化
 */
@SuppressWarnings("serial")
public class AngelAuth2ExceptionSerializer extends StdSerializer<AngelAuth2Exception> {
	public AngelAuth2ExceptionSerializer() {
		super(AngelAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(AngelAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
