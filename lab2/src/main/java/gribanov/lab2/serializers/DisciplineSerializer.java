package gribanov.lab2.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gribanov.lab2.models.Discipline;

import java.io.IOException;

public class DisciplineSerializer extends StdSerializer<Discipline> {
    public DisciplineSerializer() {
        this(null);
    }

    public DisciplineSerializer(Class<Discipline> t) {
        super(t);
    }

    @Override
    public void serialize(Discipline discipline, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", discipline.getId());
        jsonGenerator.writeStringField("name", discipline.getName());
        jsonGenerator.writeStringField("code", discipline.getCode());
        jsonGenerator.writeStringField("typePass", discipline.getTypePass());

        if (discipline.getTeacher() != null) {
            jsonGenerator.writeObjectFieldStart("teacher");
            jsonGenerator.writeNumberField("id", discipline.getTeacher().getId());
            jsonGenerator.writeStringField("fio", discipline.getTeacher().getFio());
            jsonGenerator.writeNumberField("age", discipline.getTeacher().getAge());
            jsonGenerator.writeStringField("gender", discipline.getTeacher().getGender());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("teacher");
        }

        jsonGenerator.writeEndObject();
    }
}

