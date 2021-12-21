package com.luanvv.jpa.tips.jpahibernatetip.entity;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "AUTHOR_BOOK")
@Getter @Setter
@NoArgsConstructor
public class AuthorBook {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "ID", columnDefinition = "CHAR(32)")
  private String id;

  @CreatedDate
  @Column(name = "CREATED_DATE")
  private LocalDateTime createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  private Author author;

  public AuthorBook(Author author, Book book) {
    this.book = book;
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorBook that = (AuthorBook) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(createdDate, that.createdDate) &&
        Objects.equals(book, that.book) &&
        Objects.equals(author, that.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdDate, book, author);
  }
}
