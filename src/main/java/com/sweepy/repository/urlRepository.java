package com.sweepy.repository;

import com.sweepy.database.urlTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<urlTable, Integer> {
    String findByLongUrl(String shortUrl);
    String findByShortUrl(String longUrl);
}
