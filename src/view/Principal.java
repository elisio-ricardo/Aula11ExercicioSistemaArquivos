package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {

		IArquivosController teste = new ArquivosController();
		String arquivo = "cadastro.csv";
		int codigo = 0;
		String nome = "mailon";
		String email = "mailon.com";
		try {
//			teste.verificaDirTemp();
//			teste.verificaRegistro(arquivo, codigo);
//			teste.imprimeCadastro(arquivo, codigo);
			teste.insereCadastro(arquivo, codigo, nome, email);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
