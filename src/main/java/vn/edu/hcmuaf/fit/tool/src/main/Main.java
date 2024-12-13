package vn.edu.hcmuaf.fit.tool.src.main;

import vn.edu.hcmuaf.fit.tool.src.model.Model;
import vn.edu.hcmuaf.fit.tool.src.view.View;
import vn.edu.hcmuaf.fit.tool.src.controller.Controller;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View("Đồ án cuối kỳ");
		new Controller(model, view);
	}
}