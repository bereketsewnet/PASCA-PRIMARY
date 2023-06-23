package com.example.pasca_primary.Model;

public class pdfClass {

    public String name_pdf, url;

    public pdfClass() {
    }

    public pdfClass(String name_pdf, String url) {
        this.name_pdf = name_pdf;
        this.url = url;
    }

    public String getName_pdf() {
        return name_pdf;
    }

    public void setName_pdf(String name_pdf) {
        this.name_pdf = name_pdf;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
