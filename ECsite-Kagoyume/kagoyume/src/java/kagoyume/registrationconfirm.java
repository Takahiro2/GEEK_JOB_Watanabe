package kagoyume;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * registrationconfirmに対応するサーブレット
 */
@WebServlet(name = "registrationconfirm", urlPatterns = {"/registrationconfirm"})
public class registrationconfirm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(); //セッションスタート
        request.setCharacterEncoding("UTF-8"); // 文字コードをUTF-8に

        try {

            // 不正アクセスチェック
            String acschk = request.getParameter("ac");
            System.out.println(request.getParameter("ac"));
            if (acschk.equals("null") || (Integer) session.getAttribute("ac") != Integer.parseInt(acschk)) {
                throw new Exception("不正なアクセスです");
            }

            if (UserDataDAO.check(request.getParameter("name")) != null) {
                session.setAttribute("registered", "registred");
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                
            } else {
            // UserDataBeansにユーザー情報を格納
            UserData ud = new UserData();
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("password"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));

            // ユーザー情報をセッションに格納
            session.setAttribute("ud", ud);
            System.out.println("session updated");

            request.getRequestDispatcher("/registrationconfirm.jsp").forward(request, response);

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
