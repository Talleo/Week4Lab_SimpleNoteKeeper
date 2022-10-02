package models;

import java.io.Serializable;

/**
 *
 * @author godim
 */
public class Note implements Serializable {
    private String title;
    private String content;

// constructors
    
    /**
     * public no-arg constructor
     */
    public Note() {
    }

    /**
     * overloaded constructor
     * @param title
     * @param content 
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

// getters/setters
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
