package com.example.MarkdownApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.MarkdownApp.bean.NoteBean;

public interface MarkdownAppService {
  public ResponseEntity<?> checkGrammarOfNote(NoteBean note);
  public NoteBean saveNoteDetail(NoteBean note);
  public List<NoteBean> getAllNotesDetails();
  public NoteBean getNoteDetailById(int id);
  
}
