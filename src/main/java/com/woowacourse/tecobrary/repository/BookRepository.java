package com.woowacourse.tecobrary.repository;

import com.woowacourse.tecobrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByTitleContains(String keyword);
}
