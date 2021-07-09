package dao.impl;

import bean.User;
import dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public User findUserByusernameAndPassword(User user) {
		String sql = "SELECT id,username,PASSWORD,email FROM t_user WHERE username= ? AND PASSWORD= ? ";
		return queryOne(sql, user.getUsername(),user.getPassword());
	}
	@Override
	public int saveUser(User user) {
		String sql = "INSERT INTO t_user(username,`password`,email) VALUES(?,?,?)";
		return update(sql, user.getUsername(), user.getPassword(),user.getEmail());
	}
	@Override
	public boolean existsUserName(String username) {
		String sql = "select * from t_user where username = ?";
		return (queryOne(sql, username) == null) ? false: true;
	}
	@Override
	public User findAdminByusernameAndPassword(User user) {
		String sql = "SELECT id,username,PASSWORD,email FROM t_admin WHERE username= ? AND PASSWORD= ? ";
		return queryOne(sql, user.getUsername(),user.getPassword());
	}

	

}
