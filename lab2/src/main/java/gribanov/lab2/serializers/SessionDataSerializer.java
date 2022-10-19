package gribanov.lab2.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gribanov.lab2.models.SessionData;

import java.io.IOException;

public class SessionDataSerializer extends StdSerializer<SessionData> {
    public SessionDataSerializer() {
        this(null);
    }

    public SessionDataSerializer(Class<SessionData> t) {
        super(t);
    }

    @Override
    public void serialize(SessionData sessionData, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", sessionData.getId());
        jsonGenerator.writeNumberField("mark", sessionData.getMark());

        if (sessionData.getStudent() != null) {
            jsonGenerator.writeObjectFieldStart("student");
            jsonGenerator.writeNumberField("id", sessionData.getStudent().getId());
            jsonGenerator.writeStringField("fio", sessionData.getStudent().getFio());
            jsonGenerator.writeStringField("numberRecordBook", sessionData.getStudent().getNumberRecordBook());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("student");
        }

        if (sessionData.getDiscipline() != null) {
            jsonGenerator.writeObjectFieldStart("discipline");
            jsonGenerator.writeNumberField("id", sessionData.getDiscipline().getId());
            jsonGenerator.writeStringField("code", sessionData.getDiscipline().getCode());
            jsonGenerator.writeStringField("name", sessionData.getDiscipline().getName());
            jsonGenerator.writeStringField("typePass", sessionData.getDiscipline().getTypePass());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("discipline");
        }

        jsonGenerator.writeEndObject();
    }
}
