package com.example.nekit.wantedvinyl;

public class QueryResponse {

    public static final QueryResponse UNSUCCESSFUL = new QueryResponse();

    private final boolean successful;
    private final String data;

    public QueryResponse(String data){
        this.successful = true;
        this.data = data;
    }

    public QueryResponse(){
        this.successful = false;
        this.data = "";
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getData() {
        return data;
    }
}
