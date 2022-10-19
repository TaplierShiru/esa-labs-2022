package gribanov.lab2.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import gribanov.lab2.models.Specialization;
import gribanov.lab2.models.StudentGroup;

import java.io.IOException;

public class SpecializationSerializer extends StdSerializer<Specialization> {
    public SpecializationSerializer() {
        this(null);
    }

    public SpecializationSerializer(Class<Specialization> t) {
        super(t);
    }

    @Override
    public void serialize(Specialization specialization, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", specialization.getId());
        jsonGenerator.writeStringField("code", specialization.getCode());
        jsonGenerator.writeStringField("code", specialization.getName());

        if (specialization.getGroupSet() != null && specialization.getGroupSet().size() != 0) {
            jsonGenerator.writeArrayFieldStart("groupSet");
            for (StudentGroup studentGroup : specialization.getGroupSet()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", studentGroup.getId());
                jsonGenerator.writeStringField("code", studentGroup.getCode());
                jsonGenerator.writeNumberField("course", studentGroup.getCourse());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        } else {
            jsonGenerator.writeNullField("groupSet");
        }

        jsonGenerator.writeEndObject();
    }
}
