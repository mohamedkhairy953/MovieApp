package com.example.moham.movieapp_volley.model;

public class Trailer_Model extends BaseModel{
    private int id;
    private Trailer_ModelResults[] results;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trailer_ModelResults[] getResults() {
        return this.results;
    }

    public void setResults(Trailer_ModelResults[] results) {
        this.results = results;
    }
}
