package com.woowacourse.tecobrary.repository;

import com.woowacourse.tecobrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
