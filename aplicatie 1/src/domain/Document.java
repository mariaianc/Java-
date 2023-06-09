package domain;

public class Document {

    private int id;
    private String name;
    private String keyWords;
    private String content;

    public Document(int id, String name, String keyWords, String content) {
        this.id = id;
        this.name = name;
        this.keyWords = keyWords;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return
                name + '\'' + " " + keyWords ;
    }
}
