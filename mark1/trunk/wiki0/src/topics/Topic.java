package topics;

import java.io.*;
import java.util.*;
import java.util.Date;

public class Topic 
{
    private Long id;
    private String name;
    private String text;
    private Date created;
    private Date modified;

    public Topic() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }


    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    // users that have contributed to this topic

    private Set authors = new HashSet();

    public Set getAuthors() {
      return authors;
    }

    public void setAuthors(Set authors) {
      this.authors = authors;
    }
}


