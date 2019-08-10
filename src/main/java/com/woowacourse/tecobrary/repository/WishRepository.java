package com.woowacourse.tecobrary.repository;

import com.woowacourse.tecobrary.domain.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<Wish> findByTitle(String title);
}
