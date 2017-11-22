package com.internousdev.template.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemComplateDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware{

	public Map<String,Object> buyItemInfoSession = new HashMap<String, Object>();

	public String result;

	public BuyItemComplateDAO buyItemComplateDAO = new BuyItemComplateDAO();

	/**
	 * 商品購入情報登録メソッド
	 *
	 * @author internous
	 */
	public String execute() throws SQLException {

		buyItemComplateDAO.buyItemeInfo(
				buyItemInfoSession.get("id").toString(),
				buyItemInfoSession.get("login_user_id").toString(),
				buyItemInfoSession.get("buyItem_price").toString(),
				buyItemInfoSession.get("stock").toString(),
				buyItemInfoSession.get("pay").toString());

		result = SUCCESS;
		return result;
	}

	@Override
	public void setSession(Map<String, Object> buyItemInfoSession) {
		this.buyItemInfoSession = buyItemInfoSession;
	}
}

			/*⑮*/		/*確認画面へ*/		/*内容の確認でDAOにアクセス*/