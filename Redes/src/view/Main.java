package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();
		
		String[] os = rede.os();
		//rede.Ip(os[0]);
		
		rede.ping(os[0]);
		
		

	}

}
