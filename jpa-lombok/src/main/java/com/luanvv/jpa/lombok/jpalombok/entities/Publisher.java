package com.luanvv.jpa.lombok.jpalombok.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "PUBLISHER")
@Getter @Setter
@NoArgsConstructor
public class Publisher {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "ID", columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "NAME")
  private String name;

  @CreatedDate
  @Column(name = "CREATED_DATE")
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "LAST_MODIFIED_DATE")
  private LocalDateTime lastModifiedDate;

  @OneToMany(mappedBy = "publisher")
  private Set<Book> books;

  public Publisher(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Publisher(String name) {
    this(null, name);
  }

  @Override
  public String toString() {
    return "Publisher{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", createdDate=" + createdDate +
        ", lastModifiedDate=" + lastModifiedDate +
        ", books=" + books +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Publisher author = (Publisher) o;
    return Objects.equals(id, author.id) &&
        Objects.equals(name, author.name) &&
        Objects.equals(createdDate, author.createdDate) &&
        Objects.equals(lastModifiedDate, author.lastModifiedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createdDate, lastModifiedDate);
  }
}
