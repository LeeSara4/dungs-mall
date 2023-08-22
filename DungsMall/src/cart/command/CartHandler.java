package cart.command;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.dao.BasketDao;
import basket.model.BasketSub;
import cart.service.CartService;
import detail.service.DetailService;
import item.model.Item;
import mvc.command.CommandHandler;

public class CartHandler implements CommandHandler {
	private static final String FORM_VIEW = "/view/purchase/cart/cart.jsp";
	CartService cartService = new CartService();
	List<BasketSub> list = new ArrayList<>();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("cart 핸들러 get 지나침!");
			return processSubmit(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("cart 핸들러  post 지나침!");
			
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		
		setItem(req, res);
		return FORM_VIEW;
	}

	private void setItem(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		 DetailService ds = new DetailService();
		 String sessionId = (String)req.getAttribute("sessionId");
		 list = cartService.itemCall(ds.getId(sessionId));
		 req.setAttribute("basketList", list);
		 
		 req.setCharacterEncoding("UTF-8");
		 String itemName = req.getParameter("itemName");
		 cartService.deleteBasket(ds.getId(sessionId), itemName);
	}
}



