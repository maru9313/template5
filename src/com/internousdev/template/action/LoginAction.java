package com.internousdev.template.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.template.dao.BuyItemDAO;
import com.internousdev.template.dao.LoginDAO;
import com.internousdev.template.dto.BuyItemDTO;
import com.internousdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ログイン認証処理
 * Login.jspからログインID、ログインパスワードを受け取り
 * DBへ問い合わせを行います。
 *
 * @author internous
 * @param loginUserId
 * @param loginPassword
 *
 * @return result
 */
public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * ログインID
	 */
	public String loginUserId;

	/**
	 * ログインパスワード
	 */
	public String loginPassword;

	/**
	 * 処理結果を格納
	 */
	public String result;

	/**
	 * ログイン情報を格納
	 */
	public Map<String, Object> loginUserInfoMap = new HashMap<>();

	/**
	 * ログイン情報取得DAO
	 */
	public LoginDAO loginDAO = new LoginDAO();

	/**
	 * ログイン情報格納IDTO
	 */
	private LoginDTO loginDTO = new LoginDTO();

	/**
	 * アイテム情報を取得
	 */
	public BuyItemDAO buyItemDAO = new BuyItemDAO();

	/**
	 * 実行メソッド
	 */
	public String execute() {

		result = ERROR;

		// ログイン実行
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		/*　　　↑入力されたid,psをここに持ってきてDAOを介して内容を照合する*/
		  /* その内容をDTOという箱に入れる*/

		loginUserInfoMap.put("loginUser", loginDTO);

		// ログイン情報を比較
		if(((LoginDTO) loginUserInfoMap.get("loginUser")).getLoginFlg()) {
			result = SUCCESS;

			// アイテム情報を取得
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			loginUserInfoMap.put("login_user_id",	loginDTO.getLoginId());
			loginUserInfoMap.put("id", buyItemDTO.getId());
			loginUserInfoMap.put("buyItem_name", buyItemDTO.getItemName());
			loginUserInfoMap.put("buyItem_price", buyItemDTO.getItemPrice());

			return result;
		}

		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Override
	public void setSession(Map<String, Object> loginUserInfoMap) {
		this.loginUserInfoMap = loginUserInfoMap;
	}
}

					/*⑨*/	/*新規登録を行った後そのIDが合ってるかの処理*//* DTOと照らし合わせる*/	/*BuyItemDAO/DTOを作成する*/