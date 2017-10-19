package kagoyume;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * buycomplete.jspに対応するサーブレット
 */
@WebServlet(name = "buycomplete", urlPatterns = {"/buycomplete"})
public class buycomplete extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // セッションスタート
        request.setCharacterEncoding("UTF-8"); // 文字コードをUTF-8に

        try {

            // 不正アクセスチェック
            String acschk = request.getParameter("ac");
            if (acschk.equals("null") || (Integer) session.getAttribute("ac") != Integer.parseInt(acschk)) {
                throw new Exception("不正アクセスです");
            }

            // 発送方法を取得
            String radioType = request.getParameter("radiotype");
            // userIDを取得
            UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
            int id = udd.getUserID();
            // 総購入金額を取得
            int total = udd.getTotal();

            // 買い物カゴから情報をUserDataDTOへ
            ArrayList<ProductData> cartList = (ArrayList<ProductData>) session.getAttribute(String.valueOf(udd.getUserID()));

            for (ProductData pro : cartList) {
                UserDataDTO dto = new UserDataDTO();
                dto.setUserID(id);
                dto.setItemCode(pro.getCode());
                dto.setType(Integer.parseInt(radioType));

                // DB操作にてbuy_tへInsert
                UserDataDAO.getInstance().buyInsert(dto);
            }

            // 合計の購入金額も合わせてInsert
            int cartTotal = Integer.parseInt(request.getParameter("total"));
            total += cartTotal;
            udd.setTotal(total);
            UserDataDAO.getInstance().totalUp(udd);

            // アクセス用とcartList用のセッション破棄
            session.removeAttribute("ac");
            session.removeAttribute(String.valueOf(udd.getUserID()));

            request.getRequestDispatcher("/buycomplete.jsp").forward(request, response); // buycomplete.jspへ

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
