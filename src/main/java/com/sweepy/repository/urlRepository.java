package com.sweepy.repository;

import com.sweepy.database.urlTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface urlRepository extends JpaRepository<urlTable, Integer> {
    urlTable findByShortUrl(String shortUrl);
    urlTable findByLongUrl(String longUrl);
}
