
package com.seveneleven.mycontactapp.contact.tag;

import java.util.Objects;

public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o) return true;

        if(!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
