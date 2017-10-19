package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * mydeleteresult.jspに対応するサーブレット
 */

@WebServlet(name = "mydeleteresult", urlPatterns = {"/mydeleteresult"})
public class mydeleteresult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); // セッションスタート

        try {

            request.setCharacterEncoding("UTF-8"); // 文字コードをUTF-8に
            // 不正アクセスチェック
            String acschk = request.getParameter("ac");
            if (acschk.equals("null") || (Integer) session.getAttribute("ac") != Integer.parseInt(acschk)) {
                throw new Exception("不正なアクセスです");
            }

            // ユーザーIDを取得
            UserDataDTO udd = (UserDataDTO) session.getAttribute("welcomeUDD");
            int userID = udd.getUserID();

            // 削除処理
            UserDataDAO.getInstance().delete(userID);

            // welcomeUDDとacのセッション削除
            session.removeAttribute("welcomeUDD");
            session.removeAttribute("ac");

            // ログアウト状態にする
            session.setAttribute("login", null);

            request.getRequestDispatcher("/mydeleteresult.jsp").forward(request, response);

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
