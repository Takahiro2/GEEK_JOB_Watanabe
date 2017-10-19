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
 * add.jspに対応するサーブレット
 */
@WebServlet(name = "add", urlPatterns = {"/add"})
public class add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // セッションスタート
        
        // UserIDをセッションで利用する為情報を取り出す
        UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
        // ArrayList cartList 初期化
        ArrayList<ProductData> cartList = new ArrayList<>();
        // 初めて買い物カゴに入れる場合 NULLでエラーになるので空のArrayListを作成
        if (cartList == null) {
            cartList = new ArrayList();
        }

        // ログインしていない場合login.jspへ している場合はadd.jspへ遷移
        if (session.getAttribute("login") == null) {
            session.setAttribute("noadd", "ログインしてください");
            session.setAttribute("returnURL", "cart");
            // 商品情報を買い物カゴへ追加
            cartList.add((ProductData) session.getAttribute("productData"));
            session.setAttribute("cartList", cartList);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {

            // 今までに買い物カゴへ入れた商品情報を取得
            cartList = (ArrayList<ProductData>) session.getAttribute(String.valueOf(udd.getUserID()));
            // 商品情報を買い物カゴへ追加
            cartList.add((ProductData) session.getAttribute("productData"));
            session.setAttribute(String.valueOf(udd.getUserID()), cartList);

            request.getRequestDispatcher("/add.jsp").forward(request, response); // add.jspへ遷移

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
