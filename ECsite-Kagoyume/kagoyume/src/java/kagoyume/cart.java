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
 * cart.jspに対応するサーブレット
 */

@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class cart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // セッションスタート

        // ログインしていない場合
        if (session.getAttribute("login") == null || !(session.getAttribute("login").equals("in"))) {
            session.setAttribute("page", "cart");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        // cart.jspにて削除を実行した場合ここで削除処理を行う
        if (request.getParameter("delete") != null) {
            // 削除する商品のコードを取得
            String deleteCode = request.getParameter("delete");
            // UserIDをセッションにて利用する為取り出す
            UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
            // ArrayListに格納後
            ArrayList<ProductData> cartList = (ArrayList) session.getAttribute(String.valueOf(udd.getUserID()));
            // 拡張for文 valueにて
            for (ProductData value : cartList) {
                // 商品コードが同じ商品情報を見つけた場合
                if (deleteCode.equals(value.getCode())) {
                    // その情報をdeleteDataに格納
                    ProductData deleteData = value;
                    // 消去
                    cartList.remove(deleteData);
                    break;
                }
            }
        }

        // UserIDをセッションにて利用する為取り出す
        UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
        // 買い物カゴが空の時の為の処理 NULL対策
        ArrayList<ProductData> pd = new ArrayList<>();
        // pb に買い物カゴに入っている情報を格納
        if (session.getAttribute(String.valueOf(udd.getUserID())) != null) {
            pd = (ArrayList<ProductData>) session.getAttribute(String.valueOf(udd.getUserID()));
        }
        // session(udd.getUserID())に買い物カゴ情報
        session.setAttribute(String.valueOf(udd.getUserID()), pd);

        request.getRequestDispatcher("/cart.jsp").forward(request, response); // cart.jspへ遷移

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
