package com.example.MarkdownApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MarkdownApp.bean.NoteBean;
import com.example.MarkdownApp.service.MarkdownAppService;



@RestController
public class MarkdownAppController {
	@Autowired
	MarkdownAppService markdownAppService;
	
	// to check grammar of note
     @RequestMapping(value="/check-grammar",method=RequestMethod.POST)
     public ResponseEntity<?> checkGrammar(@RequestBody NoteBean note)
     {
    	 System.out.println("Checking Grammar in controller");
    	return markdownAppService.checkGrammarOfNote(note);
     }
     
     // to save note
     @RequestMapping(value="/save-note",method=RequestMethod.POST)
     public String saveNote(@RequestBody NoteBean note)
     {
    	 System.out.println("NOTE:"+note);
    	if(markdownAppService.saveNoteDetail(note)!=null)
    		return "Note Successfully saved!";
    	else
    		 return "We are not able to save note!";
     }
     
     // list all notes
     @RequestMapping(value="/note/all",method=RequestMethod.GET)
     public List<NoteBean> getAllNotes()
     {
    	 return markdownAppService.getAllNotesDetails();
     }
     
     // list note by id
     @RequestMapping(value="/note",method=RequestMethod.GET)
     public NoteBean getNoteById(@RequestParam ("id")int id)
     {
    	 System.out.println("id:"+id);
    	return markdownAppService.getNoteDetailById(id);
     }
     
}
