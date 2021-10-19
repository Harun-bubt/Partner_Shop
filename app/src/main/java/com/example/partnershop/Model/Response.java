package com.example.partnershop.Model;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element(name = "response")
public class Response {
    @Attribute(name = "id")
    private String id;
    @Attribute(name = "message")
    private String message;

    public Response() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}