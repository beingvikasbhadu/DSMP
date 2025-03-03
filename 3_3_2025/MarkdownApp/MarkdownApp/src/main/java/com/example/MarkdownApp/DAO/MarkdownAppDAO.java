package com.example.MarkdownApp.DAO;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MarkdownApp.entity.Note;

public interface MarkdownAppDAO extends JpaRepository<Note, Integer> {
   
  
   
}
