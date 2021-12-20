package com.luanvv.jpa.tips.jpahibernatetip.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private String lanuageCode;

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
  @JoinColumn(name = "AUTHORS_ID")
  private Set<AuthorBook> authors;

}
