package com.example.MarkdownApp.service;

import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.MarkdownApp.DAO.MarkdownAppDAOWrapper;
import com.example.MarkdownApp.bean.NoteBean;

@Service
public class MarkdownAppServiceImpl implements MarkdownAppService {
	@Autowired
	MarkdownAppDAOWrapper markdownAppDAOImpl;

	@Override
	public ResponseEntity<?> checkGrammarOfNote(NoteBean note) {
		JLanguageTool langTool=new JLanguageTool(new AmericanEnglish());
		
	    
		try {
			// check title grammar mistakes
			List<RuleMatch>matchesTitle=langTool.check(note.getTitle());
			NoteBean responseNote=new NoteBean();
			System.out.println("Before__________");
			
			if(matchesTitle.isEmpty())
				responseNote.setTitle("No Grammar Mistake Found!");
			else
			{
				String correctedTitle=note.getTitle();
				StringBuilder sb=new StringBuilder(correctedTitle);
				
					matchesTitle.stream().forEach(match->{
					String replacementTitle=match.getSuggestedReplacements().get(0);
					sb.replace(match.getFromPos(),match.getToPos(),replacementTitle);
				});
				responseNote.setTitle(sb.toString());
			}
			System.out.println("After________");
			
			// check description grammar mistake
			List<RuleMatch> matchesDescription=langTool.check(note.getDescription());
			
			if(matchesDescription.isEmpty())
			{
				responseNote.setDescription("No Grammar Mistake Found!");
				
			}
			else
			{
				String correctedDescription=note.getDescription();
				StringBuilder sb=new StringBuilder(correctedDescription);
						matchesDescription.stream().forEach(match->{
					String replacementDescription=match.getSuggestedReplacements().get(0);
					System.out.println("ReplacementDescription:"+replacementDescription);
					sb.replace(match.getFromPos(), match.getToPos(), replacementDescription);
					
				});
				
				responseNote.setDescription(sb.toString());
			}
			return  ResponseEntity.ok().body(responseNote);
		}
		catch(Exception e)
		{
			return ResponseEntity.internalServerError().body("An unexpected error occurred. Please try again.");

		}
		
	}

	@Override
	public NoteBean saveNoteDetail(NoteBean note) {
		
		try {
			return markdownAppDAOImpl.saveNoteToDb(note);
		}
		catch(Exception e)
		{
			System.out.println("ERROR:::"+e.getMessage());
			return null;
		}
	}

	@Override
	public List<NoteBean> getAllNotesDetails() {
		try {
			return markdownAppDAOImpl.getAllNotesFromDb();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public NoteBean getNoteDetailById(int id) {
		try {
			return markdownAppDAOImpl.getNoteByIdFromDb(id);
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
