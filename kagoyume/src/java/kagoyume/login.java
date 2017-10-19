package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * login.jspに対応するサーブレット ログインしていない場合はlogin.jspへ ログインしている場合はログアウト処理後、top.jspへ
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // セッション開始
        response.setContentType("text/html;charset=UTF-8"); // 文字コードをUTF-8に
        
        try {

            // ログインしていない場合 login.jspへ
            if (session.getAttribute("login") == null) {

                session.setAttribute("ac", (int) (Math.random() * 1000)); // アクセスルートチェック用乱数
                request.getRequestDispatcher("/login.jsp").forward(request, response); // login.jspへ
                
              // ログインしている場合 top.jspへ
            } else {

                // 買い物カゴ以外のセッション情報破棄 冗長になるが
                session.removeAttribute("login");
                session.removeAttribute("ac");
                session.removeAttribute("search");
                session.removeAttribute("product");
                session.removeAttribute("totalPrice");
                session.removeAttribute("welcomeUDD");
                session.removeAttribute("productData");
                session.removeAttribute("returnURL");

                request.getRequestDispatcher("/top.jsp").forward(request, response); // top.jspへ

            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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
