package com.luanvv.jpa.tips.jpahibernatetip.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "ID", columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "AVERAGE_RATING")
  private BigDecimal averageRating;

  @Column(name = "ISBN")
  private String isbn;

  @Column(name = "ISBN13")
  private String isbn13;

  @Column(name = "LANGUAGE_CODE")
  private String languageCode;

  @Column(name = "NUM_PAGES")
  private Long numPages;

  @Column(name = "RATINGS_COUNT")
  private Long ratingCount;

  @Column(name = "PUBLICATION_DATE")
  private LocalDate publicationDate;

  @Column(name = "PUBLISHER")
  private String publisher;

  @CreatedDate
  @Column(name = "CREATED_DATE")
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "LAST_MODIFIED_DATE")
  private LocalDateTime lastModifiedDate;

  @OneToMany(mappedBy = "book")
  private Set<AuthorBook> authors;

  public Book(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public Book(String title, BigDecimal averageRating, String isbn, String isbn13,
      String languageCode, Long numPages, Long ratingCount, LocalDate publicationDate,
      String publisher, Set<AuthorBook> authors) {
    this.title = title;
    this.averageRating = averageRating;
    this.isbn = isbn;
    this.isbn13 = isbn13;
    this.languageCode = languageCode;
    this.numPages = numPages;
    this.ratingCount = ratingCount;
    this.publicationDate = publicationDate;
    this.publisher = publisher;
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(id, book.id) &&
        Objects.equals(title, book.title) &&
        Objects.equals(averageRating, book.averageRating) &&
        Objects.equals(isbn, book.isbn) &&
        Objects.equals(isbn13, book.isbn13) &&
        Objects.equals(languageCode, book.languageCode) &&
        Objects.equals(numPages, book.numPages) &&
        Objects.equals(ratingCount, book.ratingCount) &&
        Objects.equals(publicationDate, book.publicationDate) &&
        Objects.equals(publisher, book.publisher) &&
        Objects.equals(createdDate, book.createdDate) &&
        Objects.equals(lastModifiedDate, book.lastModifiedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, averageRating, isbn, isbn13, languageCode, numPages, ratingCount,
        publicationDate, publisher, createdDate, lastModifiedDate);
  }
}
