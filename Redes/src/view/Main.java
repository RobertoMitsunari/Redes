package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();
		//rede.Ip("p");
		String[] os = rede.os();
		System.out.println(os[0]);
		

	}

}
