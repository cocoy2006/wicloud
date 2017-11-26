package molab.main.java.service;

import molab.main.java.dao.UserinfoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

	@Autowired
	private UserinfoDAO ud;
	
}
