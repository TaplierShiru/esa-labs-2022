package gribanov.lab2.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gribanov.lab2.models.SessionData;
import gribanov.lab2.models.Student;
import gribanov.lab2.models.StudentGroup;

import java.io.IOException;

public class StudentGroupSerializer extends StdSerializer<StudentGroup> {
    public StudentGroupSerializer() {
        this(null);
    }

    public StudentGroupSerializer(Class<StudentGroup> t) {
        super(t);
    }

    @Override
    public void serialize(StudentGroup studentGroup, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", studentGroup.getId());
        jsonGenerator.writeStringField("code", studentGroup.getCode());
        jsonGenerator.writeNumberField("course", studentGroup.getCourse());

        if (studentGroup.getSpecialization() != null) {
            jsonGenerator.writeObjectFieldStart("specialization");
            jsonGenerator.writeNumberField("id", studentGroup.getSpecialization().getId());
            jsonGenerator.writeStringField("code", studentGroup.getSpecialization().getCode());
            jsonGenerator.writeStringField("name", studentGroup.getSpecialization().getName());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("specialization");
        }

        if (studentGroup.getStudentSet() != null && studentGroup.getStudentSet().size() != 0) {
            jsonGenerator.writeArrayFieldStart("studentSet");
            for (Student student : studentGroup.getStudentSet()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", student.getId());
                jsonGenerator.writeStringField("numberRecordBook", student.getNumberRecordBook());
                jsonGenerator.writeStringField("fio", student.getFio());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        } else {
            jsonGenerator.writeNullField("sessionSet");
        }

        jsonGenerator.writeEndObject();
    }
}
