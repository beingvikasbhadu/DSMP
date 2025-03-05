package com.example.MarkdownApp.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.MarkdownApp.bean.NoteBean;
import com.example.MarkdownApp.entity.Note;

@Repository
public class MarkdownAppDAOWrapper  {
    @Autowired
	private  MarkdownAppDAO markdownAppDAO;

	
	
	public NoteBean saveNoteToDb(NoteBean note) throws Exception {
		Note entity=beanToEntity(note);
		Note saved_entity=markdownAppDAO.save(entity);
		return entityToBean(saved_entity);
	}

	
	public List<NoteBean> getAllNotesFromDb() throws Exception{
		List<Note> list_notes=markdownAppDAO.findAll();
		List<NoteBean>list_bean=new ArrayList<>();
		
		for(Note note:list_notes)
			list_bean.add(entityToBean(note));
		
		return list_bean;
	}
	

	public NoteBean getNoteByIdFromDb(Integer id) throws Exception {
		System.out.println("IIIID"+id);
		Note entity=markdownAppDAO.findById(id).orElse(null);
		System.out.println("Entity:"+entity);
		return entityToBean(entity);
	}
	
	Note beanToEntity(NoteBean bean) throws Exception
	{
		Note entity = new Note();
		BeanUtils.copyProperties(bean, entity);
	    return entity;
	}
	
	NoteBean entityToBean(Note entity) throws Exception
	{
		NoteBean bean=new NoteBean();
		BeanUtils.copyProperties(entity, bean);
		return bean;
	}
}
