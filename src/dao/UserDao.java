package dao;

import bean.User;

public interface UserDao {

	/**
	 * 根据用户名 密码查询用户
	 * @param user
	 * @return
	 */
	public User findUserByusernameAndPassword(User user);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public int saveUser(User user);
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public boolean existsUserName(String username);

	/**
	 * 根据用户名 密码查询管理员
	 * @param user
	 * @return
	 */
	public User findAdminByusernameAndPassword(User user);
}
