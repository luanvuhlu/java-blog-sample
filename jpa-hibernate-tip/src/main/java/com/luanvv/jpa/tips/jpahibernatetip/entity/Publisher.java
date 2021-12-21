package com.luanvv.jpa.tips.jpahibernatetip.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
