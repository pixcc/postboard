package ru.postboard.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(max = 60)
    private String name;

    /**
     * @noinspection unused
     */
    public Tag() {
    }

    public Tag(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id
               && name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
