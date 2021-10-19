package com.example.partnershop.Model;

import org.simpleframework.xml.Element;

@Element(name = "service")
public class Service {
    @Element(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ClassPojo [response = " + response + "]";
    }
}

