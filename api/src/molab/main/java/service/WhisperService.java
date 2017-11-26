package molab.main.java.service;

import molab.main.java.dao.WhisperDAO;
import molab.main.java.entity.Whisper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhisperService {
	
	@Autowired
	private WhisperDAO dao;
	
	public String whisper(String code) {
		Whisper whisper = dao.findByCode(code);
		if(whisper != null) {
			return whisper.getMonid();
		}
		return null;
	}
	
}
