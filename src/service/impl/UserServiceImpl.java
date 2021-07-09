package service.impl;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

	
	protected UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public User findUserByUsernameAndPassword(User user) {
		return userDao.findUserByusernameAndPassword(user);
	}

	@Override
	public boolean saveUser(User user) {
		int updateCount = userDao.saveUser(user);
        return updateCount > 0 ? true : false;
	}

	@Override
	public boolean existsUserName(String username) {
		return userDao.existsUserName(username);
	}

	@Override
	public User findAdminByUsernameAndPassword(User user) {
		return userDao.findAdminByusernameAndPassword(user);
	}

}
