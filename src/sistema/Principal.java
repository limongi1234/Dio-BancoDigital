package sistema;


import armazenamento.File;

import java.io.IOException;

public class Principal 
{


    public static void main(String[] args) throws InterruptedException, IOException 
    {

    	File.hashMapFromFileTextUsuario(File.getPathBasico() +
    			"lista" + File.getEXTENSAO());
    	
    	File.hashMapFromFileTextContas(File.getPathBasico() +
    			"contas" + File.getEXTENSAO());
        
        SistemaInterno sistema = new SistemaInterno();
        sistema.menuPrincipal();

    }
}