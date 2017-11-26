package molab.main.java.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import molab.main.java.component.MSIComponent;
import molab.main.java.dao.ItemDAO;
import molab.main.java.dao.MonSubItemDAO;
import molab.main.java.dao.MonindexDAO;
import molab.main.java.dao.RssinfoDAO;
import molab.main.java.dao.SubItemDAO;
import molab.main.java.dao.UserinfoDAO;
import molab.main.java.dao.WhisperDAO;
import molab.main.java.entity.Item;
import molab.main.java.entity.MonSubItem;
import molab.main.java.entity.Monindex;
import molab.main.java.entity.Rssinfo;
import molab.main.java.entity.SubItem;
import molab.main.java.entity.Userinfo;
import molab.main.java.entity.Whisper;
import molab.main.java.util.Constants;
import molab.main.java.util.DateUtil;
import molab.main.java.util.Status;
import molab.main.java.util.Wicloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private MonindexDAO md;
	
	@Autowired
	private RssinfoDAO rd;

	@Autowired
	private UserinfoDAO ud;
	
	@Autowired
	private ItemDAO id;
	
	@Autowired
	private SubItemDAO sid;
	
	@Autowired
	private MonSubItemDAO msid;
	
	@Autowired
	private WhisperDAO wd;
	
//	public int add(String username, String monid, String monName,
//			String monAdd, int subItemId, int wallDis, String code) {
//		try {
//			// check monindex
//			Monindex example = md.findById(monid);
//			if(example != null) {
//				return Status.Add.ERROR_MON_EXIST.getInt();
//			}
//			// check whisper
//			if(wd.findByCode(code) != null) {
//				return Status.Add.ERROR_CODE_EXIST.getInt();
//			}
//			// add monindex
//			Monindex monindex = new Monindex();
//			monindex.setMonid(monid);
//			monindex.setMonAdd(monAdd);
//			monindex.setWallDis(wallDis);
//			monindex.setUserName(username);
//			monindex.setMonname(monName);
//			monindex.setGroupid(1);
//			monindex.setTotal(11);
//			md.save(monindex);
//			
//			// add rssinfo
//			Rssinfo rss = new Rssinfo();
//			rss.setMonname(monName);
//			rss.setMonid(monid);
//			rss.setRssin(-60f);
//			rss.setRssout(-100f);
//			rd.save(rss);
//			
//			// add mon_sub_item
//			MonSubItem msi = new MonSubItem();
//			msi.setMonid(monid);
//			msi.setSubItemId(subItemId);
//			msid.save(msi);
//			
//			// add whisper
//			Whisper whisper = new Whisper();
//			whisper.setMonid(monid);
//			whisper.setCode(code);
//			wd.save(whisper);
//			
//			return Status.Add.SUCCESS.getInt();
//		} catch(Exception e) {
//			e.getStackTrace();
//		}
//		return Status.Add.ERROR.getInt();
//	}
	
	public int add(String username, String monid) {
		try {
			// check monindex
			Monindex monindex = md.findById(monid);
			if(monindex != null) {
				return Status.Add.ERROR_MON_EXIST.getInt();
			} else {
				monindex = new Monindex();
				monindex.setMonid(monid);
				monindex.setUserName(username);
				monindex.setGroupid(1);
				monindex.setTotal(11);
				md.save(monindex);
				
				// add rssinfo
				Rssinfo rss = new Rssinfo();
				rss.setMonid(monid);
				rss.setRssin(-60f);
				rss.setRssout(-100f);
				rd.save(rss);
				
				return Status.Add.SUCCESS.getInt();
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Add.ERROR.getInt();
	}
	
	public int addAgent(String userName, String passwd, String shopadd, String shopName, String contract) {
		try {
			Userinfo user = ud.findByUserName(userName);
			if(user != null) {
				return Status.Common.ERROR_EXIST.getInt();
			}
			user = new Userinfo();
			user.setUserName(userName);
			user.setPasswd(passwd);
			user.setUserType(Constants.USER_TYPE_AGENT);
			user.setShopadd(shopadd);
			user.setShopName(shopName);
			user.setContract(contract);
			ud.save(user);
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int edit(String monid, String monName, String monAdd, 
			int subItemId, int wallDis, String code) {
		try {
			// check whisper
			if(wd.findByCode(code) != null) {
				return Status.Add.ERROR_CODE_EXIST.getInt();
			}
			// update monindex
			Monindex monindex = md.findById(monid);
			monindex.setMonname(monName);
			monindex.setMonAdd(monAdd);
			monindex.setWallDis(wallDis);
			md.update(monindex);
			
			// update rssinfo if exist
			Rssinfo rss = rd.findByMonid(monid);
			if(rss != null) {
				rss.setMonname(monName);
				rd.update(rss);
			} else {
				// or add rssinfo
				rss = new Rssinfo();
				rss.setMonname(monName);
				rss.setMonid(monid);
				rss.setRssin(-60f);
				rss.setRssout(-100f);
				rd.save(rss);
			}
			
			// update mon_sub_item if exist
			MonSubItem msi = msid.findByMonid(monid);
			if(msi != null) {
				msi.setSubItemId(subItemId);
				msid.update(msi);
			} else {
				// or add mon_sub_item instead
				msi = new MonSubItem();
				msi.setMonid(monid);
				msi.setSubItemId(subItemId);
				msid.save(msi);
			}
			
			// update whisper if exist
			Whisper whisper = wd.findByMonid(monid);
			if(whisper != null) {
				whisper.setCode(code);
				wd.update(whisper);
			} else {
				// add whisper instead
				whisper = new Whisper();
				whisper.setMonid(monid);
				whisper.setCode(code);
				wd.save(whisper);
			}
			
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int editAgent(String userName, String shopadd, String shopName, String contract) {
		try {
			Userinfo user = ud.findByUserName(userName);
			user.setShopadd(shopadd);
			user.setShopName(shopName);
			user.setContract(contract);
			ud.update(user);
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public List<MSIComponent> findAll(String searchString) {
		List<Monindex> monList = md.findAll(searchString);
		if(monList != null && monList.size() > 0) {
			List<MSIComponent> msicList = new ArrayList<MSIComponent>();
			for(Monindex mon : monList) {
				MSIComponent msic = findByMon(mon);
				msicList.add(msic);
			}
			return msicList;
		}
		return null;
	}
	
	public List<MSIComponent> findAll(String username, String searchString) {
		List<Monindex> monList = md.findAll(username, searchString);
		if(monList != null && monList.size() > 0) {
			List<MSIComponent> msicList = new ArrayList<MSIComponent>();
			for(Monindex mon : monList) {
				MSIComponent msic = findByMon(mon);
				msicList.add(msic);
			}
			return msicList;
		}
		return null;
	}
	
	public List<Item> findAllItem() {
		return id.findAll();
	}
	
	public List<SubItem> findAllSubItem() {
		return sid.findAll();
	}
	
	public MSIComponent findByMon(Monindex mon) {
		if(mon != null) {
			MSIComponent msic = new MSIComponent();
			// add Monindex
			msic.setMon(mon);
			// add MonSubItem
			MonSubItem msi = msid.findByMonid(mon.getMonid());
			if(msi != null) {
				// add SubItem
				int subItemId = msi.getSubItemId();
				SubItem subItem = sid.findById(subItemId);
				if(subItem != null) {
					msic.setSubItem(subItem);
					// add Item
					Item item = id.findById(subItem.getItemId());
					if(item != null) {
						msic.setItem(item);
					}
				}
			}
			// add Whisper
			msic.setWhisper(wd.findByMonid(mon.getMonid()));
			return msic;
		}
		return null;
	}
	
	public MSIComponent findByMonid(String monid) {
		Monindex mon = md.findById(monid);
		return findByMon(mon);
	}
	
	public Userinfo findByUserName(String userName) {
		return ud.findByUserName(userName);
	}
	
	public List<Userinfo> findByUserType(String userType) {
		return ud.findByUserType(userType);
	}
	
	public void findNormalAndAbnormal(List<MSIComponent> all, 
			List<MSIComponent> normal, List<MSIComponent> abnormal) {
		if(all != null && all.size() > 0) {
			String dataPath = Wicloud.getProperty(Constants.CFG_DATA_PATH);
			for(MSIComponent msic : all) {
				Monindex mon = msic.getMon();
				String monid = mon.getMonid();
				if(!isValid(dataPath, monid)) {
					abnormal.add(msic);
				} else {
					normal.add(msic);
				}
			}
		}
	}
	
	/**
	 * 文件不存在或修改时间已超过100秒 */
	private boolean isValid(String dataPath, String monid) {
		File data = new File(dataPath + monid + File.separator + DateUtil.day());
		if(!data.exists() || 
				System.currentTimeMillis() - data.lastModified() > 100000) {
			return false;
		}
		return true;
	}
	
	public int pwd(String username, String passwdOri, String passwd) {
		try {
			Userinfo user = ud.findByUserName(username);
			if(user != null) {
				if(!user.getPasswd().equalsIgnoreCase(passwdOri)) {
					return Status.Signin.ERROR_PASSWORD.getInt();
				} else {
					user.setPasswd(passwd);
					ud.update(user);
					return Status.Common.SUCCESS.getInt();
				}
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int recover(String monid) {
		try {
			// update mon
			Monindex mon = md.findById(monid);
			if(mon != null) {
				mon.setMonAdd(null);
				mon.setWallDis(null);
				mon.setUserName(Constants.ADMIN_USERNAME);
				mon.setMonname(null);
				md.update(mon);
			}
			
			// remove rssinfo if exist
			Rssinfo rss = rd.findByMonid(monid);
			if(rss != null) {
				rd.remove(rss);
			}
			
			// remove mon_sub_item if exist
			MonSubItem msi = msid.findByMonid(monid);
			if(msi != null) {
				msid.remove(msi);
			}
			
			// remove whisper if exist
			Whisper whisper = wd.findByMonid(monid);
			if(whisper != null) {
				wd.remove(whisper);
			}
			
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int remove(String monid) {
		try {
			Monindex monindex = md.findById(monid);
			if(monindex != null) {
				monindex.setUserName(Constants.DISCARD_USERNAME);
				md.update(monindex);
			}
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	public int removeAgent(String userName) {
		try {
			// recover all mons first
			List<Monindex> list = md.findByUsername(userName);
			for(Monindex mon : list) {
				mon.setUserName(Constants.ADMIN_USERNAME);
				md.update(mon);
			}
			// then discard user
			Userinfo user = ud.findByUserName(userName);
			if(user != null) {
				ud.remove(user);
			}
			return Status.Common.SUCCESS.getInt();
		} catch(Exception e) {
			e.getStackTrace();
		}
		return Status.Common.ERROR.getInt();
	}
	
	/**
	 * @deprecated */
	private float rssi(float dis) {
		if(dis <= 1) {
			return -10f;
		} else if(dis <= 1.3895) {
			return -15f;
		} else if(dis <= 1.9307) {
			return -20f;
		} else if(dis <= 2.6827) {
			return -25f;
		} else if(dis <= 3.7276) {
			return -30f;
		} else if(dis <= 5.1795) {
			return -35f;
		} else if(dis <= 7.1969) {
			return -40f;
		} else if(dis <= 10) {
			return -45f;
		} else if(dis <= 13.895) {
			return -50f;
		} else if(dis <= 19.307) {
			return -55f;
		} else if(dis <= 26.827) {
			return -60f;
		} else if(dis <= 37.2759) {
			return -65f;
		} else if(dis <= 51.7947) {
			return -70f;
		} else if(dis <= 71.9686) {
			return -75f;
		} else if(dis <= 100) {
			return -80f;
		} else {
			return -85f;
		}
	}
	
	public int signin(String username, String password, String userType) {
		if(Constants.USER_TYPE_USER.equalsIgnoreCase(userType) 
				&& !"huimei".equals(username)) {
			return Status.Common.ERROR.getInt();
		}
		Userinfo example = new Userinfo();
		example.setUserName(username);
		example.setPasswd(password);
		example.setUserType(userType);
		List<Userinfo> list = ud.findByExample(example);
		if(list != null && list.size() > 0 
				&& list.get(0).getUserType().equalsIgnoreCase(userType)) {
			return Status.Common.SUCCESS.getInt();
		}
		return Status.Common.ERROR.getInt();
	}
	
}
