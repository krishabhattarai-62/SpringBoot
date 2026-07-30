package io.herald.springboot.Repository;

import io.herald.springboot.Model.ImageTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageTable, Integer> {

}
