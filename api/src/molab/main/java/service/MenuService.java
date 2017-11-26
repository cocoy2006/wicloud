package molab.main.java.service;

import java.util.List;

import molab.main.java.dao.MenuDAO;
import molab.main.java.entity.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

	@Autowired
	private MenuDAO md;
		
	public List<Menu> findByUsername(String username) {
		return md.findByUsername(username);
	}
	
}
