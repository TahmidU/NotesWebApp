package com.tahmidu.notes_web_app.repository;

import com.tahmidu.notes_web_app.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository extends PagingAndSortingRepository<Note, Long> {

    @Query(value = "SELECT * FROM note n WHERE (LOWER(n.title) LIKE %:search%) OR (LOWER(n.content) LIKE %:search%)",
    countQuery = "SELECT COUNT(*) FROM note n WHERE (LOWER(n.title) LIKE %:search%) OR (LOWER(n.content) LIKE %:search%)",
    nativeQuery = true)
    Page<Note> findNotesByQuery(Pageable pageable, String search);

}
