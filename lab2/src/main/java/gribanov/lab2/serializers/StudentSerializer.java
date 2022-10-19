package gribanov.lab2.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gribanov.lab2.models.SessionData;
import gribanov.lab2.models.Student;

import java.io.IOException;

public class StudentSerializer extends StdSerializer<Student> {
    public StudentSerializer() {
        this(null);
    }

    public StudentSerializer(Class<Student> t) {
        super(t);
    }

    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", student.getId());
        jsonGenerator.writeStringField("numberRecordBook", student.getNumberRecordBook());

        if (student.getStudentGroup() != null) {
            jsonGenerator.writeObjectFieldStart("studentGroup");
            jsonGenerator.writeNumberField("id", student.getStudentGroup().getId());
            jsonGenerator.writeStringField("code", student.getStudentGroup().getCode());
            jsonGenerator.writeNumberField("course", student.getStudentGroup().getCourse());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("studentGroup");
        }

        if (student.getSessionSet() != null && student.getSessionSet().size() != 0) {
            jsonGenerator.writeArrayFieldStart("sessionSet");
            for (SessionData sessionData : student.getSessionSet()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", sessionData.getId());
                jsonGenerator.writeNumberField("mark", sessionData.getMark());

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
            jsonGenerator.writeEndArray();
        } else {
            jsonGenerator.writeNullField("sessionSet");
        }

        jsonGenerator.writeEndObject();
    }
}
