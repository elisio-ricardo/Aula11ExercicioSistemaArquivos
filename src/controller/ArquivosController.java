package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void verificaDirTemp() throws IOException {
		boolean bool = false;
		File dir = new File("C:/TEMP/exercicio");
		File arq = new File(dir, "cadastro.csv");
		if (dir.exists() && dir.isDirectory()) {
			System.out.println("Diretorio Existe");
		} else {
			bool = dir.mkdirs();
			System.out.println("Diretorio Criado " + bool);
		}
		String conteudo = geraCadastro();
		FileWriter fileWriter = new FileWriter(arq);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(conteudo);
		print.flush();
		print.close();
		fileWriter.close();
	}

	private String geraCadastro() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while (!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "Digite o Codigo;Nome;Email ", "Entrada de Dados",
					JOptionPane.INFORMATION_MESSAGE);
			if (!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + "\r\n");
			}
		}

		return buffer.toString();
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File("C:\\TEMP\\exercicio", arquivo);
		String cod = Integer.toString(codigo);
		boolean existe = false;
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains(cod)) {
					existe = true;
				}
				linha = buffer.readLine();
			}

			buffer.close();
			fluxo.close();
			leitor.close();
		} else {
			throw new IOException("Diretorio Invalido");
		}
		return existe;

	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		File arq = new File("C:\\TEMP\\exercicio", arquivo);
		String cod = Integer.toString(codigo);
		IArquivosController teste = new ArquivosController();
		if (teste.verificaRegistro(arquivo, codigo)) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();// 
			while (linha != null) {
				if (linha.contains(cod)) {
					dividelinha(linha);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			fluxo.close();
			leitor.close();

		}

	}

	private void dividelinha(String linha) {
		String[] divideLinha = linha.split(";");
		System.out.println("Codigo: " + divideLinha[0]);
		System.out.println("Nome: " + divideLinha[1]);
		System.out.println("Email: " + divideLinha[2]);

	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File arq = new File("C:\\TEMP\\exercicio", arquivo);
		IArquivosController cadastro = new ArquivosController();
		if (cadastro.verificaRegistro(arquivo, codigo) == false) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			String linha = adiciona(codigo, nome, email);
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(linha);
			print.flush();
			print.close();
			fileWriter.close();
		}else {
			System.out.println("Cadastro já existe");
		}
	}

	private String adiciona(int codigo, String nome, String email) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(codigo + ";" + nome + ";" + email);

		return buffer.toString();
	}
}
