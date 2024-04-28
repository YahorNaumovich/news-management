package edu.training.web.controller.concrete.impl;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegInfo;
import edu.training.web.controller.concrete.Command;
import edu.training.web.logic.LogicStub;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DoRegistration implements Command {

	private final LogicStub logic = new LogicStub();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("confirmPassword");

		System.out.println("Perform user authentication and authorization. Login: " + login);

		User user = logic.checkReg(new UserRegInfo(login, email, password, passwordConfirm));

		if (user != null) {
			HttpSession session = (HttpSession) request.getSession(true);
			session.setAttribute("user", user);

			response.sendRedirect("Controller?command=go_to_main_page");

		} else {
			response.sendRedirect("Controller?command=go_to_registration_page&authError=Passwords do not match");
		}
	}

}
