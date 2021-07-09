package service;


import bean.User;

public interface UserService {

	/**
	 * 根据用户名 密码查询用户
	 * @param user
	 * @return
	 */
	public User findUserByUsernameAndPassword(User user);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user);
	
	/**
	 * 验证用户名已存在
	 * @param username
	 * @return
	 */
	public boolean existsUserName(String username);
	
	/**
	 * 根据用户名 密码查询管理员
	 * @param t
	 * @return
	 */
	public User findAdminByUsernameAndPassword(User t);
}
