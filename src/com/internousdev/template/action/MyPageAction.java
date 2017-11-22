package com.internousdev.template.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.MyPageDAO;
import com.internousdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	/**
	 * ログイン情報を格納
	 */
	public Map<String, Object>  loginInfoMap = new HashMap<>();

	/**
	 * マイページ情報取得DAO
	 */
	public MyPageDAO myPageDAO = new MyPageDAO();

	/**
	 * マイページ情報格納DTO
	 */
	public MyPageDTO myPageDTO = new MyPageDTO();

	/**
	 * 削除フラグ
	 */
	public String deleteFlg;

	/**
	 * 処理結果
	 */
	public String result;

	/**
	 * 商品履歴取得メソッド
	 *
	 * @author internous
	 */
	public String execute() throws SQLException {

		// 商品履歴を削除しない場合
		if(deleteFlg == null) {
			String item_transaction_id = loginInfoMap.get("id").toString();
			String user_master_id = loginInfoMap.get("login_user_id").toString();

			myPageDTO = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

			loginInfoMap.put("total_price", myPageDTO.getTotalPrice());
			loginInfoMap.put("total_count", myPageDTO.getTotalCount());
			loginInfoMap.put("total_payment", myPageDTO.getPayment());

		// 商品履歴を削除する場合
		} else if(deleteFlg.equals("1")) {
			delete();
		}

		result = SUCCESS;
		return result;
	}

	/**
	 * 商品履歴削除
	 *
	 * @throws SQLException
	 */
	public void delete() throws SQLException {

		System.out.println("delete");
		String item_transaction_id = loginInfoMap.get("id").toString();
		String user_master_id = loginInfoMap.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(item_transaction_id, user_master_id);

		if(res > 0) {
			loginInfoMap.put("message", "商品情報を正しく削除しました。");
		} else if(res == 0) {
			loginInfoMap.put("message", "商品情報の削除に失敗しました。");
		}
	}



	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	@Override
	public void setSession(Map<String, Object> loginSessionMap) {
		this.loginInfoMap = loginSessionMap;
	}
}
