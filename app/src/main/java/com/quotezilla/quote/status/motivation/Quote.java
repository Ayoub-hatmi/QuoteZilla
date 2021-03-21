package com.quotezilla.quote.status.motivation;

public class Quote {
    private String content;
    private String writer;
    private String topic;
    private int id;



    public Quote(){

    }

    public Quote(String content, String writer, String topic, int id) {

        this.id=id;
        this.content = content;
        this.writer = writer;
        this.topic = topic;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
