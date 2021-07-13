package com.tahmidu.notes_web_app.repository;

import com.tahmidu.notes_web_app.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteRepository extends PagingAndSortingRepository<Note, Long> {

    @Query(value = "SELECT * FROM note n WHERE n.title = :search OR n.content = :search",
    countQuery = "SELECT * FROM note n WHERE n.title = :search OR n.content = :search",
    nativeQuery = true)
    Page<Note> getNotesByQuery(Pageable pageable, String search);

}
